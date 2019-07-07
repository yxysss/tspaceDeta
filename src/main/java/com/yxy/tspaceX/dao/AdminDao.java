package com.yxy.tspaceX.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.yxy.tspaceX.bean.Admin;

public interface AdminDao {
	
	public Admin selectAdminByAdminname(@Param("adminname") String adminname) throws DataAccessException;

	public HashMap<String, Object> selectPadAdminByPadAdminname(@Param("padadminname") String padadminname) throws DataAccessException;
	
	public Integer selectCountOfPassApplicationByCondition(@Param("idroom") Integer idroom, @Param("password") String password, @Param("now") String now) throws DataAccessException;
	
	public List<HashMap<String, Object>> selectRoomListForPad() throws DataAccessException;
	
	public Integer insertWebData(@Param("counttype") Integer counttype, @Param("totalcount") Integer totalcount, @Param("countdate") String countdate) throws DataAccessException;
}
