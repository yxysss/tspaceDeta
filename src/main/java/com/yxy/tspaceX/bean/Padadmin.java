package com.yxy.tspaceX.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Padadmin {

	private Integer idpadadmin;
	
	private String padadminname;
	
	private String password;

	public String getpadadminname() {
		return padadminname;
	}

	public void setpadadminname(String padadminname) {
		this.padadminname = padadminname;
	}

	public Integer getidpadadmin() {
		return idpadadmin;
	}

	public void setidpadadmin(Integer idpadadmin) {
		this.idpadadmin = idpadadmin;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}
	
}
