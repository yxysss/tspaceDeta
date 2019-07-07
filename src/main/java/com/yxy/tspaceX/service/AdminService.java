package com.yxy.tspaceX.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxy.tspaceX.bean.Admin;
import com.yxy.tspaceX.dao.AdminDao;
import com.yxy.tspaceX.util.roomupdate;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	public Admin selectAdminByAdminname(String adminname) {
		try {
			 return adminDao.selectAdminByAdminname(adminname);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
	public HashMap<String, Object> selectPadAdminByPadAdminname(String padadminname) {
		try {
			return adminDao.selectPadAdminByPadAdminname(padadminname);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
	public Integer selectCountOfPassApplicationByCondition(Integer idroom, String password, String now) {
		try {
			return adminDao.selectCountOfPassApplicationByCondition(idroom, password, now);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
	public List<HashMap<String, Object>> selectRoomListForPad() {
		try {
			return adminDao.selectRoomListForPad();
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
	public Integer insertWebData(Integer counttype, Integer totalcount, String countdate) {
		try {
			return adminDao.insertWebData(counttype, totalcount, countdate);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}

}
