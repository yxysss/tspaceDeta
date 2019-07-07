package com.yxy.tspaceX.bean;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Application implements Comparable<Application> {

	private Integer idapplication;
	private Integer idroom;
	private Date starttime;
	private Date endtime;
	private String applicant; // 与User表的username建立外键
	private String reason;
	private String participants;
	
	// 0: 接受
	// 1: 拒绝
	// 2: 未审核
	// 3: 取消
	private Integer state;
	private Date applytime; // 记录创建时间
	private Date audittime; // 记录修改时间
	
	public Integer getidapplication() {
		return idapplication;
	}
	
	public Integer getidroom() {
		return idroom;
	}
	
	public Date getstarttime() {
		return starttime;
	}
	
	public Date getendtime() {
		return endtime;
	}
	
	public String getapplicant() {
		return applicant;
	}
	
	public String getreason() {
		return reason;
	}
	
	public String getparticipants() {
		return participants;
	}
	
	public Integer getstate() {
		return state;
	}
	
	public Date getapplytime() {
		return applytime;
	}
	
	public Date getaudittime() {
		return audittime;
	}
	
	public void setidapplication(Integer idapplication) {
		this.idapplication = idapplication;
	}
	
	public void setidroom(Integer idroom) {
		this.idroom = idroom;
	}
	
	public void setstarttime(Date starttime) {
		this.starttime = starttime;
	}
	
	public void setendtime(Date endtime) {
		this.endtime = endtime;
	}
	
	public void setapplicant(String applicant) {
		this.applicant = applicant;	
	}
	
	public void setreason(String reason) {
		this.reason = reason;
	}
	
	public void setparticipants(String participants) {
		this.participants = participants;
	}
	
	public void setstate(Integer state) {
		this.state = state;
	}
	
	public void setapplytime(Date applytime) {
		this.applytime = applytime;
	}
	
	public void setaudittime(Date audittime) {
		this.audittime = audittime;
	}
	
	
	// 定义compareTo方法，便于排序
	// java中定义排序的方法
	// 1. compareTo方法
	// 2. 某接口？
	@Override
	public int compareTo(Application application) {
		if (idroom < application.getidroom()) return 1;
		if (idroom > application.getidroom()) return -1;
		if (starttime.getTime() < application.getstarttime().getTime()) return 1;
		if (starttime.getTime() > application.getstarttime().getTime()) return -1;
		if (endtime.getTime() < application.getendtime().getTime()) return 1;
		if (endtime.getTime() > application.getendtime().getTime()) return -1;
		if (applytime.getTime() < application.getapplytime().getTime()) return 1;
		return -1;
	}
}
