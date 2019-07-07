package com.yxy.tspaceX.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.yxy.tspaceX.bean.User;

public interface UserDao {

	public List<HashMap<String, Object>> selectAll(@Param("searchentry") String searchentry, @Param("start") Integer start, @Param("size") Integer size) throws DataAccessException;
	
	public HashMap<String, Object> selectUserByUsername(@Param("username") String username) throws DataAccessException;
	
	public Integer updateUserPassword(@Param("username") String username, @Param("password")String password) throws DataAccessException;
	
	public Integer insertUser(User user) throws DataAccessException;
	
	// public Integer calladdproc(HashMap<String, Object> map) throws DataAccessException;
	
	public HashMap<String, Object> selectUserByMobile(@Param("mobile") String mobile) throws DataAccessException;

	public Integer selectCountOfUser() throws DataAccessException;
	
	public Integer updateUsernameNullByMobile(@Param("mobile") String mobile) throws DataAccessException;
	
	public Integer insertUserIntoBlackList(@Param("mobile") String mobile) throws DataAccessException;
	
	public Integer selectCountOfUserByCondition(@Param("searchentry") String searchentry) throws DataAccessException;

	public Integer insertUserRecord(@Param("recordtype") Integer recordtype, @Param("recorddate") String recorddate, @Param("recorduser") String recorduser) throws DataAccessException;
	
}
