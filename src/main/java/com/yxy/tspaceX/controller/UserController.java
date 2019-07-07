package com.yxy.tspaceX.controller;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxy.tspaceX.bean.School;
import com.yxy.tspaceX.bean.User;
import com.yxy.tspaceX.service.ApplicationService;
import com.yxy.tspaceX.service.RoomService;
import com.yxy.tspaceX.service.SchoolService;
import com.yxy.tspaceX.service.UserService;
import com.yxy.tspaceX.util.dateformat;
import com.yxy.tspaceX.util.mail;
import com.yxy.tspaceX.util.pin;
import com.yxy.tspaceX.util.roomupdate;
import com.yxy.tspaceX.util.sms;


/**
 * 所有网页请求，请求方法均为GET，面向在浏览器中输入网址访问
 * 所有提交数据，请求方法均为POST，面向javascript中ajax请求
 * @author yxy
 * return 用户页面
 */
@Controller
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private SchoolService schoolService;
	
	/**
	 * @param param
	 * @return 解析JSON
	 */
	@SuppressWarnings("unchecked")
	private HashMap<String, Object> resolveJson(String param) {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> map = null;
		try {
			map = mapper.readValue(param, HashMap.class);
		} catch (IOException e) {
			roomupdate.logger.error(e.toString());
		}
		return map;
	}
	
	/**
	 * @return 注册成功页面
	 */
	@RequestMapping(value="/applysuccess", method=RequestMethod.GET)
	public String applysuccess() {
		return "/user/applysuccess";
	}
	
	/**
	 * 需要用户登录后才可访问
	 * @return 修改密码页面
	 */
	@RequestMapping(value="/changepassword", method=RequestMethod.GET)
	public String changepassword(HttpSession session) {
		// 检查是否登录
		String username = (String) session.getAttribute("username");
		if (username == null) {
			// 未登录直接返回登录页面
			return "redirect:/login";
		}
		return "/user/changepassword";
	}

	
	/**
	 * 返回忘记密码中的验证码图片
	 */
	@RequestMapping(value="/getforgetpasswordpin", method=RequestMethod.GET)
	public void getforgetpasswordpin(HttpServletResponse response, HttpSession session) {
		response.setContentType("image/jpeg");
		int width=140, height=70;
		BufferedImage image = pin.getVerifyImage(width, height, session, "forgetpasswordpin");
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			roomupdate.logger.error(e.toString());
		}
		return ;
	}

	
	/**
	 * 获取忘记密码中短信验证码
	 */
	@ResponseBody
	@RequestMapping(value="/getforgetpasswordsmsverify", method=RequestMethod.POST)
	public Integer getforgetpasswordsmsverify(HttpSession session) {
		Long forgetpasswordpintime = (Long) session.getAttribute("forgetpasswordpintime");
		if (forgetpasswordpintime == null) {
			// 无效访问-未通过验证码验证
			return -2;
		}
		Long currenttime = System.currentTimeMillis();
		int timediff = (int) ((currenttime - forgetpasswordpintime) / 1000);
		if (timediff > 5 * 60) {
			// 无效访问-验证码超时
			return -2;
		}
		String mobile = (String) session.getAttribute("forgetpasswordmobile");
		if (mobile == null || mobile.length() != 11) {
			// 无效访问-手机号格式错误
			// 一般保存到的都是正确的，一般只有可能为null
			return -3;
		}
		Long lasttime = (Long) session.getAttribute("forgetpasswordsmsverifytime");
		if (lasttime != null) {
			timediff = (int) ((currenttime - lasttime) / 1000);
			if (timediff < 60) {
				// 无效访问-获取验证码时间间隔不足60秒
				return -2;
			}
		}
		int forgetpasswordsmsverify = sms.getCode();
		// System.out.println(forgetpasswordsmsverify);
		boolean flag = //true;
				sms.sendVerifyMessage(mobile, forgetpasswordsmsverify);
		if (flag) {
			session.setAttribute("mobile", mobile);
			session.setAttribute("forgetpasswordsmsverify", forgetpasswordsmsverify);
			session.setAttribute("forgetpasswordsmsverifytime", currenttime);
			// 短信发送成功
			return 0;
		} else {
			// 短信发送失败
			return -1;
		}

	}
	
	/**
	 * @return 登录页面
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "/user/login";
	}
	
	/**
	 * @param
	 * @return 用户登录提交
	 */
	@ResponseBody
	@RequestMapping(value="/loginsubmit", method=RequestMethod.POST)
	public Integer loginsubmit(@RequestBody String param, HttpSession session) {
		HashMap<String, Object> map = resolveJson(param);
		if (map == null) {
			// 无效访问-JSON解析失败
			return -2;
		}
		String username = (String) map.get("username");
		String password = (String) map.get("password");
		if (username == null || password == null) {
			// 无效访问-登录信息无效
			return -2;
		}
		HashMap<String, Object> user = userService.selectUserByUsername(username);
		if (user == null) {
			// 用户名错误
			return -1;
		}
		if (password.equals((String) user.get("password"))) {
			// 记录session
			session.setAttribute("username", username);
			session.setAttribute("loginmobile", (String) user.get("mobile"));
			userService.insertUserRecord(0, dateformat.timeToString(new Date()), (String) user.get("mobile"));
			roomupdate.userloginvolume.incrementAndGet();
			// 登录成功
			return 0;
		} else {
			// 登录失败-密码错误
			return -1;
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="/cancelapplication", method=RequestMethod.POST)
	public Integer cancelapplication(@RequestBody String param, HttpSession session) {
		HashMap<String, Object> map = resolveJson(param);
		if (map == null) {
			// 无效访问-JSON解析失败
			return -2;
		}
		String mobile = (String) session.getAttribute("loginmobile");
		if (mobile == null) {
			// 无效访问-未登录
			return -2;
		}
		Integer idapplication = (Integer) map.get("idapplication");
		if (idapplication == null) {
			// 无效访问-信息有误
			return -2;
		}
		Integer result = applicationService.updateApplicationStateCancel(mobile, idapplication);
		if (result == null) {
			// 取消申请失败-数据库更新失败
			return -1;
		} else {
			// 取消申请成功
			return 0;
		}
	}
	
	
	/**
	 * @return 查看申请页面
	 */
	@RequestMapping(value="/myapplication", method=RequestMethod.GET)
	public String myapplication(HttpSession session, Model model) {
		String now = dateformat.timeToString(new Date());
		String applicant = (String) session.getAttribute("loginmobile");
		// 未登录
		if (applicant == null) {
			model.addAttribute("runningapplicationlist", session.getServletContext().getAttribute("nulllist"));
			model.addAttribute("pastapplicationlist", session.getServletContext().getAttribute("nulllist"));
		} else {
			// 默认返回的List是什么List？
			List<HashMap<String, Object>> runningapplicationlist = applicationService.selectRunningApplication(applicant, now);
			List<HashMap<String, Object>> pastapplicationlist = applicationService.selectPastApplication(applicant, now);
			model.addAttribute("runningapplicationlist", runningapplicationlist);
			model.addAttribute("pastapplicationlist", pastapplicationlist);
			
		}
		return "/user/myapplication";
	}
	
	
	/**
	 * @return 用户页面
	 * 使用model.addAttribute方法等价于向request域的addAttribute方法
	 */
	@RequestMapping(value="/mytspace", method=RequestMethod.GET)
	public String mytspace(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		HashMap<String, Object> userinfo = null;
		if (username != null) {
			userinfo = userService.selectUserByUsername(username);
		}
		model.addAttribute("userinfo", userinfo);
		return "/user/mytspace";
	}
	
	/**
	 * @return 通知页面
	 */
	@RequestMapping(value="/notice", method=RequestMethod.GET)
	public String notice() {
		return "/user/notice";
	}
	
	
	/**
	 * 获取注册时需要的验证码图片
	 * @param response
	 * @param session
	 */
	@RequestMapping(value="/getregisterpin", method=RequestMethod.GET)
	public void getregisterpin(HttpServletResponse response, HttpSession session) {
		response.setContentType("image/jpeg");
		int width=140, height=70;
		BufferedImage image = pin.getVerifyImage(width, height, session, "registerpin");
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			roomupdate.logger.error(e.toString());
		}
		return ;
	}
	
	/**
	 * 获取注册时手机短信验证前的验证码验证
	 * @param param
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkregisterpin", method=RequestMethod.POST)
	public Integer checkregisterpin(@RequestBody String param, HttpSession session) {
		HashMap<String, Object> map = resolveJson(param);
		if (map == null) {
			// 无效访问-JSON解析失败
			return -2;
		}
		String registerpin = (String) session.getAttribute("registerpin");
		if (registerpin == null) {
			// 无效访问-无法获得验证码
			return -2;
		}
		if (registerpin.equalsIgnoreCase((String) map.get("registerpin"))) {
			// 记录验证码验证通过的时间
			session.setAttribute("registerpintime", System.currentTimeMillis());
			// 验证成功
			return 0;
		} else {
			// 验证失败
			return -1;
		}
	}
	
	/**
	 * 获取注册时的手机短信验证码
	 * @param param
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getregistersmsverify", method=RequestMethod.POST)
	public Integer getregistersmsverify(@RequestBody String param, HttpSession session) {
		Long registerpintime = (Long) session.getAttribute("registerpintime");
		if (registerpintime == null) {
			// 无效访问-未通过验证码验证
			return -2;
		}
		Long currenttime = System.currentTimeMillis();
		int timediff = (int)((currenttime-registerpintime)/1000);
		if (timediff > 3*60) {
			// 图片验证码有效时间为3分钟
			// 无效访问-验证码超时
			return -2;
		}
		Long lasttime = (Long) session.getAttribute("registersmsverifytime");
		if (lasttime != null) {
			timediff = (int)((currenttime - lasttime)/1000);
			if (timediff < 60) {
				// 无效访问-获取短信验证码时间间隔不足60秒
				return -2;
			}
		}
		HashMap<String, Object> map = resolveJson(param);
		if (map == null) {
			// 无效访问-JSON解析失败
			return -2;
		}
		String mobile = (String) map.get("mobile");
		if (mobile == null || mobile.length() != 11) {
			// 无效访问-手机号格式错误
			return -3;
		}
		HashMap<String, Object> userinfo = userService.selectUserByMobile(mobile);
		if (userinfo != null) {
			// 无效访问-手机号码已被注册
			return -4;
		}
		int registersmsverify = sms.getCode();
		// System.out.println(registersmsverify);
		boolean flag = //true; 
					sms.sendVerifyMessage(mobile, registersmsverify);
		if (flag){
			session.setAttribute("mobile", mobile);
			session.setAttribute("registersmsverify", registersmsverify);
			session.setAttribute("registersmsverifytime", currenttime);
			// 短信发送成功
			return 0;
		} else {
			// 短信发送失败
			return -1;
		}
	}
	
	
	/**
	 * 验证注册时的手机短信验证码
	 * @param param
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkregistersmsverify", method=RequestMethod.POST)
	public int checkregistersmsverify(@RequestBody String param, HttpSession session) {
		// System.out.println(param);
		String mobile = (String) session.getAttribute("mobile");
		Integer registersmsverify = (Integer) session.getAttribute("registersmsverify");
		Long registersmsverifytime = (Long) session.getAttribute("registersmsverifytime");
		if (mobile == null || registersmsverify == null || registersmsverifytime == null) {
			// 无效访问-未经过短信验证码验证
			return -2;
		}
		Long currenttime = System.currentTimeMillis();
		if ((int)((currenttime-registersmsverifytime)/1000) > 3*60) {
			// 短信验证码验证有效时间为3分钟
			// 无效访问-短信验证码超时
			return -3;
		}
		HashMap<String, Object> map = resolveJson(param);
		if (map == null) {
			// 无效访问-JSON解析失败
			return -2;
		}
		if (mobile.equals((String) map.get("mobile"))) {
			if (registersmsverify.equals(Integer.valueOf((String) map.get("registersmsverify")))) {
				// 记录短信验证码验证通过时间
				session.setAttribute("register0passtime", System.currentTimeMillis());
				// 验证成功
				return 0;
			}
		}
		// 验证失败
		return -1;
	}
	
	/**
	 * 获取用户注册手机短信验证码时间
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/checkregistersmsverifytime", method=RequestMethod.POST)
	public int checkregistersmstime(HttpSession session) {
		Long lasttime = (Long) session.getAttribute("registersmsverifytime");
		if (lasttime == null) {
			return 0;
		}
		Long currenttime = System.currentTimeMillis();
		int seconds = (int)((currenttime-lasttime)/1000);
		if (seconds >= 60) {
			return 0;
		}
		return 60-seconds;
	}
	
	
	/**
	 * @return 用户注册第一步：手机号注册页面
	 */
	@RequestMapping(value="/register0", method=RequestMethod.GET)
	public String register0() {
		return "/user/register0";
	}
	
	/**
	 * @return 用户注册第二步：用户信息填写页面
	 */
	@RequestMapping(value="/register1", method=RequestMethod.GET)
	public String register1(HttpSession session, Model model) {
		Long lasttime = (Long) session.getAttribute("register0passtime");
		if (lasttime == null) {
			// 无效访问-未通过短信验证码验证
			return "/user/register0";
		}
		int timediff = (int)((System.currentTimeMillis()-lasttime)/1000);
		if (timediff > 20*60) {
			// 无效访问-短信验证码验证通过超时
			return "/user/register0";
		}
		List<School> schoollist = schoolService.selectAllSchools();
		model.addAttribute("schoollist", schoollist);
		return "/user/register1";
	}
	
	
	/**
	 * 用户注册第二步提交：用户信息填写提交
	 * user表中外键school指向school表中idschool
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/registersubmit1", method=RequestMethod.POST)
	public int registersubmit1(HttpSession session, @RequestBody User user) {
		Long lasttime = (Long)session.getAttribute("register0passtime");
		if (lasttime == null) {
			// 无效访问-未通过短信验证码验证
			return -2;
		}
		int timediff = (int)((System.currentTimeMillis()-lasttime)/1000);
		if (timediff > 20*60) {
			// 短信验证通过有效时间为20分钟
			// 无效访问-短信验证码验证超时
			return -2;
		}
		String mobile = (String) session.getAttribute("mobile");
		if (mobile == null) {
			// 无效访问-手机号在经过第一步手机短信验证码注册后，都会有。为了保险起见，再加一层判断
			return -2;
		}
		user.setmobile(mobile);
		user.setlastlogin(new Date());
		String username = user.getusername();
		String password = user.getpassword();
		String name = user.getname();
		Integer school = user.getschool();
		Integer identity = user.getidentity();
		if (username == null || password == null || name == null || school == null || identity == null) {
			// 无效访问-信息不完整
			return -2;
		}
		// 密码在前端使用md5算法，统一长度为32位
		if (password.length() != 32) {
			// 无效访问-密码信息有误
			return -2;
		}
		if (identity != 0 && identity != 1) {
			// 无效访问-身份信息有误
			return -2;
		}
		// 学生身份
		if (identity == 0) {
			// 学生学号为11位
			if (username.length() != 11) {
				// 无效访问-学号信息有误
				return -2;
			}
		} else {
			// 老师工号为4位
			if (username.length() != 4) {
				// 无效访问-工号信息有误
				return -2;
			}
		}
		HashMap<String, Object> userinfo = userService.selectUserByUsername(username);
		if (userinfo != null) {
			// 学号已被他人使用
			return -3;
		}
		// school信息在记录插入数据库时进行检查，由于设置了外键限制，如果school的id号不存在，则无法插入
		// 一般正常通过页面进行注册，不存在school信息出问题的情况
		Integer result = userService.insertUser(user);
		/*
		if (user.getiduser() == null) {
			// 注册失败-数据库添加失败
			return -1;
		} else {
			// 注册成功
			return 0;
		}
		*/
		if (result == null) {
			// 注册失败-数据库添加数据失败
			return -1;
		} else {
			// 注册成功
			return 0;
		}
	}
	
	
	/**
	 * @return 注册成功页面
	 */
	@RequestMapping(value="/registersuccess", method=RequestMethod.GET)
	public String registersuccess() {
		return "/user/registersuccess";
	}
	
	/**
	 * @return 房间信息页面
	 */
	@RequestMapping(value="/roomdisplay/idroom={idroom}", method=RequestMethod.GET)
	public String roomdisplay() {
		roomupdate.browsingvolume.incrementAndGet();
		return "/user/roomdisplay";
	}
	
	/**
	 * 获取房间信息
	 * @RequestBody 会进行属性映射，将json映射为pojo，直接获取request的body中的内容s
	 * @ResponseBody 也会进行属性映射，将pojo映射为json，直接将内容写入response的body中
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/roomdisplayinfo", method=RequestMethod.POST)
	public HashMap<String, Object> roomdisplayinfo(@RequestBody String param) {
		HashMap<String, Object> map = resolveJson(param);
		if (map == null) {
			// 无效访问-JSON解析失败
			return null;
		}
		Integer idroom = (Integer) map.get("idroom");
		if (idroom == null) {
			// 无效访问-信息有误
			return null;
		}
		HashMap<String, Object> roominfo = roomService.selectRoomById(idroom);
		return roominfo;
	}
	
	/**
	 * @param session
	 * @return 用户安全退出
	 */
	@ResponseBody
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public Boolean logout(HttpSession session) {
		String username = (String) session.getAttribute("username");
		if (username == null) {
			return false;
		}
		session.removeAttribute("username");
		session.removeAttribute("loginmobile");
		return true;
	}
	
	@RequestMapping(value="/nullroom", method=RequestMethod.GET)
	public String nullroom() {
		return "/user/nullroom";
	}
	
	
	/*
	@RequestMapping("/test")
	public void test() {
		/*
		User user = new User();
		user.setidentification("320721199511190015");
		user.setidentity(0);
		user.setiduser(null);
		user.setlastlogin(new Date());
		user.setmobile("15029029395");
		user.setname("a");
		user.setpassword("11111111111111111111111111111111");
		user.setschool(1);
		user.setusername("14010100015");
		Integer result = userService.insertUser(user);
		System.out.println(result);
		*/
		/* 如果通过HashMap，没有指定类型的情况下，需要指定jdbctype？	 */
		// Integer result = userService.updateUserPassword("14010100019", "11111111111111111111111111111111");
		// System.out.println(result);
		/*
		HashMap<String, Object> parammap = new HashMap<String, Object>();
		// IN, OUT 参数通过map传入
		parammap.put("a", 1);
		parammap.put("b", 2);
		parammap.put("c", null);
		Integer result = userService.calladdproc(parammap);
		// result=null 调用存储过程，无返回值
		System.out.println(result);
		// OUT 参数通过map获取
		System.out.println((Integer) parammap.get("c"));
	}
	*/
}
