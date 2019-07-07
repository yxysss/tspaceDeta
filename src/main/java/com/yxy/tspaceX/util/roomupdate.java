package com.yxy.tspaceX.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yxy.tspaceX.bean.Room;
import com.yxy.tspaceX.service.AdminService;
import com.yxy.tspaceX.service.RoomService;

@Component
public class roomupdate {
	
	// record date of room
	public static int year=0, month=0, day=0;
	
	public static int y, m, d;
	
	// lock of table room
	public static Object roomlock = new Object();
	
	// logger
	public static final Logger logger = LogManager.getLogger("database");
	
	// record times of web browsing
	public static AtomicInteger browsingvolume = new AtomicInteger();
	
	// record times of user login
	public static AtomicInteger userloginvolume = new AtomicInteger();
	
	// cache of classroom
	public static List<Room> classroom = new ArrayList<Room>();
	
	
	private Thread thread = null;

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private AdminService adminService;
	
	private int getInterval(Calendar cal1, Calendar cal2) {
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MILLISECOND, 0);
		cal2.set(Calendar.HOUR_OF_DAY, 0);
		cal2.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.SECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);
		return (int) ((cal2.getTimeInMillis()-cal1.getTimeInMillis())/(24*60*60*1000));
	}
	
	private class TimerThread extends Thread {
		public void run() {
			
			Date roomdate = null;
			while (1 != 0) {
				roomdate = roomService.selectTodayDateForDailyUpdate();
				if (roomdate != null) break;
			}
			
			// System.out.println("roomupdate:"+roomdate);
			
			/*
			 * 项目刚启动时，进行数据库日期和实际日期校准
			 * 计算数据库中日期与当前日期的天数差
			 */
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(roomdate);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(new Date());
			y = cal2.get(Calendar.YEAR);
			m = cal2.get(Calendar.MONTH)+1;
			d = cal2.get(Calendar.DAY_OF_MONTH);
			int interval = getInterval(cal1, cal2);
			System.out.println("interval:"+interval);
			for (int i = 0; i < interval; i ++) {
				while (1!=0) {
					Integer flag = roomService.updateAllAvailableRoom();
					// System.out.println(flag);
					if (flag == null) {
						continue;
					}
					if (flag == 0) {
						break;
					}
				}
			}
			year = y; month = m; day = d;
			
			while (1!=0) {
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				y = cal.get(Calendar.YEAR);
				m = cal.get(Calendar.MONTH)+1;
				d = cal.get(Calendar.DAY_OF_MONTH);
				
				if (y != year || m != month || d != day) {
					// 记录每一天的访问数量和登录数量
					/*
					 * counttype
					 * 0：浏览量（主页和房间信息页)
					 * 1：用户登录量
					*/
					Date pastday = new Date(System.currentTimeMillis() - 24*60*60*1000);
					while (adminService.insertWebData(0, browsingvolume.get(), dateformat.dateToString(pastday)) == null) {}
					browsingvolume.set(0);
					while (adminService.insertWebData(1, userloginvolume.get(), dateformat.dateToString(pastday)) == null) {}
					userloginvolume.set(0);
					while (1!=0) {
						Integer flag = roomService.updateAllAvailableRoom();
						// System.out.println(flag);
						if (flag == null) {
							continue;
						}
						if (flag == 0) {
							break;
						}
					}
				}
				
				year = y; month = m; day = d;  
				
				try {
					// 每1分钟查询一次
					Thread.sleep(1000*60);
				} catch (Exception e) {
					logger.error(e.toString());
				}
			}
			
		}
	}
	
	public void taskbegin() {
		// logger.error(roomlock.toString());
		thread = new TimerThread();
		thread.start();
	}
	
	public void taskstop() {
		if (thread == null) return ;
		if (thread.isAlive()) {
			thread.interrupt();
		}
		thread = null;
	}
	
}
