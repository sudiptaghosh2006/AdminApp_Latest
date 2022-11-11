package com.enterprise.services.model;

import java.io.Serializable;

public class ReaourceConfiguration implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Long id;
	private String cloudProvider;
	private String systemVersion;

	
	public String getCloudProvider() {
		return cloudProvider;
	}

	public void setCloudProvider(String cloudProvider) {
		this.cloudProvider = cloudProvider;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}