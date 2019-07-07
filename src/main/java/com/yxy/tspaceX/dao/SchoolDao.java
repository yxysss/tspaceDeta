package com.yxy.tspaceX.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.yxy.tspaceX.bean.School;

public interface SchoolDao {
	
	public List<School> selectAllSchools() throws DataAccessException;
	
	public List<HashMap<String, Object>> selectAllRoomType() throws DataAccessException;

}
