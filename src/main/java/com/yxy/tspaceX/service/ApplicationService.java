package com.yxy.tspaceX.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxy.tspaceX.dao.ApplicationDao;
import com.yxy.tspaceX.dao.RoomDao;
import com.yxy.tspaceX.util.dateformat;
import com.yxy.tspaceX.util.roomupdate;

@Service
public class ApplicationService {
	
	@Autowired
	private ApplicationDao applicationDao;
	
	@Autowired
	private RoomDao roomDao;

	public List<HashMap<String, Object>> selectRunningApplication(String applicant, String now) {
		try {
			return applicationDao.selectRunningApplication(applicant, now);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}

	public List<HashMap<String, Object>> selectPastApplication(String applicant, String now) {
		try {
			return applicationDao.selectPastApplication(applicant, now);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}

	public HashMap<String, Object> selectApplicationById(Integer idapplication) {
		try {
			return applicationDao.selectApplicationById(idapplication);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
	public Integer updateApplicationStateAccept(HashMap<String, Object> map) {
		synchronized (roomupdate.roomlock) {
			try {
				applicationDao.updateApplicationStateAccept(map);
				return 0;
			} catch (Exception e) {
				roomupdate.logger.error(e.toString());
				return null;
			}
		}
	}
	
	public Integer updateApplicationStateDecline(Integer idapplication) {
		try {
			return applicationDao.updateApplicationStateDecline(idapplication);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
	public Integer updateApplicationStateCancel(String applicant, Integer idapplication) {
		try {
			return applicationDao.updateApplicationStateCancel(applicant, idapplication);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}

	public List<HashMap<String, Object>> selectUnsettleApplicationBySection(String now, Integer start, Integer size) {
		try {
			return applicationDao.selectUnsettleApplicationBySection(now, start, size);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
	
	public Integer insertApplicationSeries(HashMap<String, Object> applicationinfo) {
		Integer starttime = (Integer) applicationinfo.get("starttime");
		Integer endtime = (Integer) applicationinfo.get("endtime");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, starttime/100);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		String thisday = dateformat.timeToString(calendar.getTime());
		calendar.add(Calendar.DATE, 1);
		String nextday = dateformat.timeToString(calendar.getTime());
		Integer count = null;
		try {
			count = applicationDao.selectApplicationCountByDate((String) applicationinfo.get("applicant"), thisday, nextday);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
		if (count > 0) {
			// 申请的日期已有申请（待审核或者已被通过）
			return -5;
		}
		synchronized (roomupdate.roomlock) {
			String availableroom = null;
			try {
				availableroom = roomDao.selectRoomAvailableById((Integer) applicationinfo.get("idroom"));
			} catch (Exception e) {
				roomupdate.logger.error(e.toString());
				return null;
			}
			int d = starttime / 100;
			int s = starttime % 100;
			int e = endtime % 100;
			for (int i = 31*(d-1)+s-1; i < 31*(d-1)+e; i ++) {
				if (availableroom.indexOf(i) == '1') {
					// 申请的时间段已被他人使用
					return -4;
				}
			}
			/* 将starttime和endtime转换为标准的date格式 */
			calendar.add(Calendar.DATE, -1);
			calendar.set(Calendar.HOUR_OF_DAY, (starttime%100)/2+7);
			calendar.set(Calendar.MINUTE, starttime%2*30);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			applicationinfo.put("starttime", calendar.getTime());
			calendar.set(Calendar.HOUR_OF_DAY, (endtime%100-1)/2+8);
			calendar.set(Calendar.MINUTE, ((endtime%2+1)%2)*30);
			applicationinfo.put("endtime", calendar.getTime());
			
			try {
				return applicationDao.insertApplication(applicationinfo);
			} catch (Exception e0) {
				roomupdate.logger.error(e0.toString());
				return null;
			}
		}
	}
	
	public Integer selectCountOfUnsettleApplication(String now) {
		try {
			return applicationDao.selectCountOfUnsettleApplication(now);
		} catch (Exception e) {
			roomupdate.logger.error(e.toString());
			return null;
		}
	}
}
