package com.capgemini.common.dto;

public class UserValidationResult {

	private String user;
	private Boolean validUser;
	private Boolean defaultUser;
	public String getUser() {
		return user;
	}
	public UserValidationResult setUser(String user) {
		this.user = user;
		return this;
	}
	public Boolean getValidUser() {
		return validUser;
	}
	public UserValidationResult setValidUser(Boolean validUser) {
		this.validUser = validUser;
		return this;
	}
	public Boolean getDefaultUser() {
		return defaultUser;
	}
	public UserValidationResult setDefaultUser(Boolean defaultUser) {
		this.defaultUser = defaultUser;
		return this;
	}
	
	
}
