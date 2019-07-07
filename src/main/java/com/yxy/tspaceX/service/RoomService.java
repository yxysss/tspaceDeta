package com.yxy.tspaceX.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yxy.tspaceX.bean.Room;
import com.yxy.tspaceX.dao.RoomDao;
import com.yxy.tspaceX.util.dateformat;
import com.yxy.tspaceX.util.roomupdate;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class RoomService {

	@Autowired
	private RoomDao roomDao;
	
	public List<HashMap<String, Object>> selectAllRooms() {
		try {
			return roomDao.selectAllRooms();
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
	public Date selectTodayDateForDailyUpdate() {
		try {
			return roomDao.selectTodayDateForDailyUpdate();
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}

	public HashMap<String, Object> selectRoomById(int idroom) {
		try {
			return roomDao.selectRoomById(idroom);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
	public Integer insertRoom(Room room) {
		try {
			return roomDao.insertRoom(room);
		} catch (DataAccessException e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
	public String uploadImage(CommonsMultipartFile image, String realUploadPath, String imageName) {
		File uploadImage = new File(realUploadPath+"/rawImages");
		if (!uploadImage.exists()) {
			uploadImage.mkdirs();
		}
		
		try {
			BufferedInputStream inputStream = new BufferedInputStream(image.getInputStream());
			
			String outputPath = realUploadPath+"/rawImages/"+imageName;
			
			System.out.println("outputPath="+outputPath);
			
			FileOutputStream outputStream = new FileOutputStream(outputPath);
			
			// 40*1024 Byte, 40KB
			byte[] buffer = new byte[40960];
			
			while ((inputStream.read(buffer)) > 0) {
				outputStream.write(buffer);
			}
			outputStream.close();
		} catch (IOException e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
		
		return "images/rawImages/"+imageName;
	}
	
	
	public String generateThumbnail(CommonsMultipartFile image, String realUploadPath, String imageName) {
		File uploadFile = new File(realUploadPath+"/thumbImages");
		
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		
		int width = 200;
		int height = 200;
		
		String des = realUploadPath+"/thumbImages/"+imageName;
		
		try {
			// 按照长宽比进行缩放，已给定的宽为基准，计算缩放后的长度
			Thumbnails.of(image.getInputStream()).size(width, height).toFile(des);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			roomupdate.logger.error(e.toString());
			return null;
		}
		return "images/thumbImages/"+imageName;
	}
	
	public Boolean renameImage(String oldpath, String newpath) {
		System.out.println("oldpath="+oldpath);
		System.out.println("newpath="+newpath);
		return new File(oldpath).renameTo(new File(newpath));
	}
	
	public Boolean renameThumbnail(String oldpath, String newpath) {
		return new File(oldpath).renameTo(new File(newpath));
	}
	
	public Integer updateAllAvailableRoom() {
		synchronized (roomupdate.roomlock) {
			String todaydate = dateformat.dateToString(new Date());
			List<HashMap<String, Object>> roomlist = null;
			try {
				roomlist = roomDao.selectUpdateRoom(todaydate);
			} catch (Exception e) {
				roomupdate.logger.error(e.toString());
				return null;
			}
			if (roomlist.size() == 0) {
				return 0;
			}
			Integer idroom;
			StringBuilder availableroom;
			StringBuilder todayroom;
			for (int i = 0; i < roomlist.size(); i ++) {
				HashMap<String, Object> roominfo = roomlist.get(i);
				idroom = (Integer) roominfo.get("idroom");
				availableroom = new StringBuilder((String) roominfo.get("availableroom"));
		    	todayroom = new StringBuilder(availableroom.substring(0, 30));
		    	availableroom = new StringBuilder(availableroom.substring(30));
				availableroom.append("000000000000000000000000000000");
				try {
					roomDao.updateAvailableRoom(availableroom.toString(), todayroom.toString(), todaydate, idroom);
				} catch (Exception e) {
					roomupdate.logger.error(e.toString());
					return null;
				}
			}
			return 0;
		}
	}
	
	public Integer selectCountOfRoom() {
		try {
			return roomDao.selectCountOfRoom();
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
	public Integer updateRoomById(Room room) {
		try {
			return roomDao.updateRoomById(room);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
	public boolean deleteImages(String filepath) {
		File file = new File(filepath);
		if (file.exists() && file.isFile()) {
			return file.delete();
		}
		return false;
	}
	
	public Room selectRoomByIdForUpdate(int idroom) {
		try {
			return roomDao.selectRoomByIdForUpdate(idroom);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
	public Integer deleteRoomById(int idroom) {
		try {
			return roomDao.deleteRoomById(idroom);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}

}
