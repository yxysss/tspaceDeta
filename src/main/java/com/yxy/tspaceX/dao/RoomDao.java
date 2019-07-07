package com.yxy.tspaceX.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.yxy.tspaceX.bean.Room;

public interface RoomDao {

	
	public List<HashMap<String, Object>> selectAllRooms() throws DataAccessException;
	
	public HashMap<String, Object> selectRoomById(@Param("idroom") int idroom) throws DataAccessException;
	
	public Integer insertRoom(Room room) throws DataAccessException;
	
	public String selectRoomAvailableById(@Param("idroom") int idroom) throws DataAccessException;
	
	public List<HashMap<String, Object>> selectUpdateRoom(@Param("todaydate") String todaydate) throws DataAccessException;
	
	public Integer updateAvailableRoom(@Param("availableroom") String availableroom, @Param("todayroom") String todayroom, @Param("todaydate") String todaydate, @Param("idroom") Integer idroom) throws DataAccessException;

	public Integer selectCountOfRoom() throws DataAccessException;
	
	public Integer updateRoomById(Room room) throws DataAccessException;
	
	public Room selectRoomByIdForUpdate(@Param("idroom") int idroom) throws DataAccessException;
	
	public Integer deleteRoomById(@Param("idroom") int idroom) throws DataAccessException;
	
	public Date selectTodayDateForDailyUpdate() throws DataAccessException;
}
