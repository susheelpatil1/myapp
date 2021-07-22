package com.laput.vo;

import org.springframework.stereotype.Component;


@Component
public class Credential {
	
	String id;
	String pwd;
	String errorid;
	String errorpwd;
	
	public String getErrorid() {
		return errorid;
	}
	public void setErrorid(String errorid) {
		this.errorid = errorid;
	}
	public String getErrorpwd() {
		return errorpwd;
	}
	public void setErrorpwd(String errorpwd) {
		this.errorpwd = errorpwd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
