package com.yxy.tspaceX.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.yxy.tspaceX.service.UserService;

@Component
@Scope("prototype")
public class mail implements Runnable {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SimpleMailMessage simpleMailMessage;
	
	public void setContent(String content) {
		simpleMailMessage.setText(content);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int trytime = 0;
		while (true) {
			Integer result = userService.sendMailOfCustomService(simpleMailMessage);
			if (result != null) break;
			trytime ++;
			// 如果重试次数超过10次，取消尝试
			if (trytime > 10) break;
		}
	}
	
	
}
