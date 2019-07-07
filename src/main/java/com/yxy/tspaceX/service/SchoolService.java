package com.yxy.tspaceX.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxy.tspaceX.bean.School;
import com.yxy.tspaceX.dao.SchoolDao;
import com.yxy.tspaceX.util.roomupdate;

@Service
public class SchoolService {
	
	@Autowired
	private SchoolDao schoolDao;
	
	public List<School> selectAllSchools() {
		
		try {
			return schoolDao.selectAllSchools();
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
	public List<HashMap<String, Object>> selectAllRoomType() {
		try {
			return schoolDao.selectAllRoomType();
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}

}
