package com.yxy.tspaceX.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class School {
	
	private int idschool;
	
	private String schoolname;

	public int getidschool() {
		return idschool;
	}
	
	public String getschoolname() {
		return schoolname;
	}

	public void setidschool(int idschool) {
		this.idschool = idschool;
	}

	public void setschoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	
	/*
	public String toString() {
		
		JSONObject jsonobject = new JSONObject();
		jsonobject.put("idschool", idschool);
		jsonobject.put("schoolname", schoolname);
		return jsonobject.toString();
	}
	*/
}
