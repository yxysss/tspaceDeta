package com.yxy.tspaceX.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dateformat {

	
	public static String timeToString(Date time){
		if (time == null) return null;
	    SimpleDateFormat formatter;
	    formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String ctime = formatter.format(time);
	    return ctime;
	}
	
	public static String dateToString(Date date) {
		if (date == null) return null;
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String ctime = formatter.format(date);
		return ctime;
	}
	
}
