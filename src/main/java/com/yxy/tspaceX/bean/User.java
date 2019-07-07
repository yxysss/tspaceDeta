package com.yxy.tspaceX.bean;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class User {
	
	private Integer iduser;
	
	private String username;
	
	private String mobile;
	
	private String identification;
	
	private String name;
	
	private String password;
	
	private Integer school;
	
	private Date lastlogin;
	
	// 0为学生
	// 1为老师
	// mysql中使用BIT(1)来存储
	// mybatis 再进行类型转换时，会把BIT转换为BOOLEAN
	// 0为false，非0为true
	private Integer identity;
	
	public Integer getiduser() {
		return iduser;
	}
	
	public String getusername() {
		return username; 
	}
	
	public String getmobile() {
		return mobile;
	}
	
	public String getidentification() {
		return identification;
	}
	
	public String getname() {
		return name;
	}
	
	public String getpassword() {
		return password;
	}
	
	public Integer getschool() {
		return school;
	}
	
	public Date getlastlogin() {
		return lastlogin;
	}
	
	public Integer getidentity() {
		return identity;
	}
	
	public void setiduser(Integer iduser) {
		this.iduser = iduser;
	}
	
	public void setusername(String username) {
		this.username = username;
	}
	
	public void setmobile(String mobile) {
		this.mobile = mobile;
	}
	
	public void setidentification(String identification) {
		this.identification = identification;
	}
	
	public void setname(String name) {
		this.name = name;
	}
	
	public void setpassword(String password) {
		this.password = password;
	}
	
	public void setschool(Integer school) {
		this.school = school;
	}

	public void setlastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	public void setidentity(Integer identity) {
		this.identity = identity;
	}

}
