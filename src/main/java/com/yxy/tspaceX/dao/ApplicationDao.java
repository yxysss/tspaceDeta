package com.yxy.tspaceX.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ApplicationDao {
	
	
	// admin
	public HashMap<String, Object> selectApplicationById(@Param("idapplication") Integer idapplication) throws DataAccessException;
	
	public List<HashMap<String, Object>> selectUnsettleApplicationBySection(@Param("now") String now, @Param("start") Integer start, @Param("size") Integer size) throws DataAccessException;
	
	public void updateApplicationStateAccept(HashMap<String, Object> map) throws DataAccessException;
	
	public Integer updateApplicationStateDecline(@Param("idapplication") Integer idapplication) throws DataAccessException;
	
	public Integer selectCountOfUnsettleApplication(@Param("now") String now) throws DataAccessException;
	
	public Integer updateBlackListApplicationStateDecline(@Param("applicant") String applicant) throws DataAccessException;
	
	// user
	public List<HashMap<String, Object>> selectRunningApplication(@Param("applicant")String applicant, @Param("now")String now) throws DataAccessException;
	
	public List<HashMap<String, Object>> selectPastApplication(@Param("applicant")String applicant, @Param("now")String now) throws DataAccessException;

	public Integer insertApplication(HashMap<String, Object> applicationinfo) throws DataAccessException;
	
	public Integer updateApplicationStateCancel(@Param("applicant") String applicant, @Param("idapplication") Integer idapplication) throws DataAccessException;
	
	public Integer selectApplicationCountByDate(@Param("applicant") String applicant, @Param("thisday") String thisday, @Param("nextday") String nextday) throws DataAccessException;
}

