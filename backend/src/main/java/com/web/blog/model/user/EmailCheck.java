package com.web.blog.model.user;

public class EmailCheck {
	private String email;
	private String code;
	private int confirm;
	
	
	public int getConfirm() {
		return confirm;
	}
	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public EmailCheck(String email, String code, int confirm) {
		super();
		this.email = email;
		this.code = code;
		this.confirm = confirm;
	}
	
	
}
