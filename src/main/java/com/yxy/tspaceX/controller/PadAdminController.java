package com.yxy.tspaceX.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxy.tspaceX.service.AdminService;
import com.yxy.tspaceX.util.dateformat;
import com.yxy.tspaceX.util.roomupdate;

@Controller
public class PadAdminController {
	
	@Autowired
	private AdminService adminService;
	
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
	 * @param param
	 * @return pad登录
	 */
	@ResponseBody
	@RequestMapping(value="/padlogin", method=RequestMethod.POST)
	public Integer padlogin(@RequestBody String param) {
		HashMap<String, Object> map = resolveJson(param);
		if (map == null) {
			// 无效访问-JSON无法解析
			return -2;
		}
		String padadminname = (String) map.get("padadminname");
		String password = (String) map.get("password");
		if (padadminname == null || password == null) {
			// 无效访问-信息有误
			return -2;
		}
		HashMap<String, Object> padadmininfo = adminService.selectPadAdminByPadAdminname(padadminname);
		if (padadmininfo == null) {
			// 用户名不存在
			return -1;
		}
		String realpadadminname = (String) padadmininfo.get("padadminname");
		String realpassword = DigestUtils.md5DigestAsHex(((String) padadmininfo.get("password")).getBytes());
		if (realpadadminname.equals(padadminname) && realpassword.equals(password)) {
			// 登录成功
			return 0;
		} else {
			// 密码错误
			return -1;
		}
	}
	
	/**
	 * @param param
	 * @return pad开门
	 */
	@ResponseBody
	@RequestMapping(value="/opendoor", method=RequestMethod.POST)
	public Integer opendoor(@RequestBody String param) {
		HashMap<String, Object> map = resolveJson(param);
		if (map == null) {
			// 无效访问-JSON无法解析
			return -2;
		}
		Integer idroom = (Integer) map.get("idroom");
		String password = (String) map.get("password");
		if (idroom == null || password == null) {
			// 无效访问-信息有误
			return -2;
		}
		String now = dateformat.timeToString(new Date());
		Integer result = adminService.selectCountOfPassApplicationByCondition(idroom, password, now);
		if (result == null) {
			// 数据库访问失败
			return -1;
		}
		if (result > 0) {
			// 开门成功
			return 0;
		} else {
			// 开门失败-无对应通过申请
			return -1;
		}
	}
	
	
	/**
	 * @return pad获取房间列表
	 */
	@ResponseBody
	@RequestMapping(value="/roomlist", method=RequestMethod.POST)
	public List<HashMap<String, Object>> roomlist() {
		List<HashMap<String, Object>> roomlist = adminService.selectRoomListForPad();
		return roomlist;
	}
	
	
	
}
