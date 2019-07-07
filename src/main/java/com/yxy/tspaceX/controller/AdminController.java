package com.yxy.tspaceX.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxy.tspaceX.bean.Admin;
import com.yxy.tspaceX.bean.Room;
import com.yxy.tspaceX.bean.School;
import com.yxy.tspaceX.service.AdminService;
import com.yxy.tspaceX.service.ApplicationService;
import com.yxy.tspaceX.service.RoomService;
import com.yxy.tspaceX.service.SchoolService;
import com.yxy.tspaceX.service.UserService;
import com.yxy.tspaceX.util.dateformat;
import com.yxy.tspaceX.util.pin;
import com.yxy.tspaceX.util.roomupdate;
import com.yxy.tspaceX.util.sms;

/**
 * @author pc
 *
 */
@Controller
@RequestMapping("/a")
public class AdminController {
	
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private AdminService adminService;
	
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
	 * @param page
	 * @param idapplication
	 * @return 返回管理员审批申请的页面
	 */
	@RequestMapping(value="/auditapplication", method=RequestMethod.GET)
	public String auditapplication(@RequestParam(value="page", required=false) Integer page, @RequestParam(value="idapplication", required=false) Integer idapplication, Model model, HttpSession session) {
		String adminname = (String) session.getAttribute("adminname");
		if (adminname == null) {
			return "redirect:/a/login";
		}
		if (page == null) page = 1;
		HashMap<String, Object> chosenapplication = null;
		if (idapplication != null) {
			chosenapplication = applicationService.selectApplicationById(idapplication);
		}
		// System.out.println(chosenapplication);
		String now = dateformat.timeToString(new Date());
		List<HashMap<String, Object>> unsettleapplicationlist = applicationService.selectUnsettleApplicationBySection(now, (page-1)*40, 40);
		Integer count = applicationService.selectCountOfUnsettleApplication(now);
		model.addAttribute("chosenapplication", chosenapplication);
		model.addAttribute("unsettleapplicationlist", unsettleapplicationlist);
		model.addAttribute("chosenidapplication", idapplication);
		model.addAttribute("page", page);
		model.addAttribute("pagecount", count/40+(count%40>0?1:0));
		return "/admin/adminauditapplication";
	}

	
	/**
	 * @param param
	 * @return 管理员通过申请
	 */
	@ResponseBody
	@RequestMapping(value="/acceptapplication", method=RequestMethod.POST)
	public Integer acceptapplication(@RequestBody String param, HttpSession session) {
		String adminname = (String) session.getAttribute("adminname");
		if (adminname == null) {
			return -3;
		}
		HashMap<String, Object> map = resolveJson(param);
		if (map == null) {
			// 无效访问-JSON解析失败
			return -2;
		}
		Integer idapplication = (Integer) map.get("idapplication");
		if (idapplication == null) {
			// 无效访问-信息有误
			return -2;
		}
		HashMap<String, Object> applicationinfo = applicationService.selectApplicationById(idapplication);
		if (applicationinfo == null) {
			// 申请不存在
			return -3;
		}
		String password = Integer.toString(sms.getCode());
		HashMap<String, Object> sqlmap = new HashMap<String, Object>();
		sqlmap.put("idapplication", idapplication);
		sqlmap.put("password", password);
		sqlmap.put("result", null);
		Integer flag = applicationService.updateApplicationStateAccept(sqlmap);
		if (flag == null) {
			// 申请通过失败-更新数据库失败
			return -1;
		}
		Integer result = (Integer) sqlmap.get("result");
		// -3：申请不存在
		// -4：房间不存在
		// -6：申请已被审核
		// 0：申请审核通过
		if (result == 0) {
			// 发送通知短信
			sms.sendApplicationMessage((String) applicationinfo.get("applicant"), (String) applicationinfo.get("name"), 
					(Date) applicationinfo.get("starttime"),
					(Date) applicationinfo.get("endtime"),
					(String) applicationinfo.get("nameroom"), password);
		}
		return result;
	}

	/**
	 * @param param
	 * @param session
	 * @return 删除房间
	 */
	@ResponseBody
	@RequestMapping(value="/deleteroom", method=RequestMethod.POST)
	public Integer deleteroom(@RequestBody String param, HttpSession session) {
		String adminname = (String) session.getAttribute("adminname");
		if (adminname == null) {
			// 无效访问-未登录
			return -3;
		}
		HashMap<String, Object> map = resolveJson(param);
		if (map == null) {
			// 无效访问-JSON解析失败
			return -2;
		}
		Integer idroom = (Integer) map.get("idroom");
		if (idroom == null) {
			// 无效访问-数据有误
			return -2;
		}
		Integer result = roomService.deleteRoomById(idroom);
		if (result == null) {
			// 删除房间失败-数据库操作失败
			return -1;
		} else {
			// 删除房间成功
			return 0;
		}
	}

}
