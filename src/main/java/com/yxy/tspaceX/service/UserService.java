package com.yxy.tspaceX.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.yxy.tspaceX.bean.User;
import com.yxy.tspaceX.dao.ApplicationDao;
import com.yxy.tspaceX.dao.UserDao;
import com.yxy.tspaceX.util.dateformat;
import com.yxy.tspaceX.util.roomupdate;

@Service
public class UserService {

	
	@Autowired
	private UserDao userDao;
	
	public List<HashMap<String, Object>> selectAll(String searchentry, Integer start, Integer size) {
		try {
			return userDao.selectAll(searchentry, start, size);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}

	public HashMap<String, Object> selectUserByUsername(String username) {
		try {
			return userDao.selectUserByUsername(username);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}

	public Integer updateUserPassword(String username, String password) {
		try {
			// 返回更新数据的行数
			return userDao.updateUserPassword(username, password);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}

	@Transactional
	public Integer insertUser(User user) {
		try {
			// 返回更新数据的行数，成功添加，返回值为1
			userDao.insertUser(user);
			// 添加注册记录
			userDao.insertUserRecord(1, dateformat.timeToString(new Date()), user.getmobile());
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return null;
		}
		return 0;
	}
	
	// For Test
	/*public Integer calladdproc(HashMap<String, Object> map) {
		try {
			return userDao.calladdproc(map);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}*/
	
	public HashMap<String, Object> selectUserByMobile(String mobile) {
		try {
			return userDao.selectUserByMobile(mobile);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
	public Integer selectCountOfUser() {
		try {
			return userDao.selectCountOfUser();
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
	public Integer selectCountOfUserByCondition(String searchentry) {
		try {
			return userDao.selectCountOfUserByCondition(searchentry);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
	@Autowired
	private ApplicationDao applicationDao;
	
	@Transactional
	public Integer adduserintoblacklist(String mobile) {
		try {
			userDao.updateUsernameNullByMobile(mobile);
			userDao.insertUserIntoBlackList(mobile);
			applicationDao.updateBlackListApplicationStateDecline(mobile);
			userDao.insertUserRecord(2, dateformat.timeToString(new Date()), mobile);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			// 手动回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return null;
		}
		return 0;
	}
	
	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;
	
	public Integer sendMailOfCustomService(SimpleMailMessage simpleMailMessage) {
		try {
			javaMailSenderImpl.send(simpleMailMessage);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
		return 0;
	}
	
	public Integer insertUserRecord(Integer recordtype, String recorddate, String recorduser) {
		try {
			return userDao.insertUserRecord(recordtype, recorddate, recorduser);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
}
