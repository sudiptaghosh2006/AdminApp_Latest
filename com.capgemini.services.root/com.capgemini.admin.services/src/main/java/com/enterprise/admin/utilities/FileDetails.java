package com.enterprise.admin.utilities;

public class FileDetails {

	private String fileName;
	private String creationTime;
	private String lastAccessedTime;
	private Long size;
	private String owener;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getLastAccessedTime() {
		return lastAccessedTime;
	}
	public void setLastAccessedTime(String lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getOwener() {
		return owener;
	}
	public void setOwener(String owener) {
		this.owener = owener;
	}
	
	
}
