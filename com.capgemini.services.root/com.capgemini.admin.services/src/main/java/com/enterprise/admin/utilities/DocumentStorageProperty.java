package com.enterprise.admin.utilities;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "configuration")
public class DocumentStorageProperty {
	
	private String uploadDirectory;
	private String userFile;
	private String awsSettingFile;
	private String azureSettingFile;
	private String gcpSettingFile;
	private String settingFileExtension;
	private String cloudProviderList;
	private String availableVersions;

	public String getCloudProviderList() {
		return cloudProviderList;
	}

	public void setCloudProviderList(String cloudProviderList) {
		this.cloudProviderList = cloudProviderList;
	}

	public String getAvailableVersions() {
		return availableVersions;
	}

	public void setAvailableVersions(String availableVersions) {
		this.availableVersions = availableVersions;
	}

	public String getSettingFileExtension() {
		return settingFileExtension;
	}

	public void setSettingFileExtension(String settingFileExtension) {
		this.settingFileExtension = settingFileExtension;
	}

	public String getAwsSettingFile() {
		return awsSettingFile;
	}

	public void setAwsSettingFile(String awsSettingFile) {
		this.awsSettingFile = awsSettingFile;
	}

	public String getAzureSettingFile() {
		return azureSettingFile;
	}

	public void setAzureSettingFile(String azureSettingFile) {
		this.azureSettingFile = azureSettingFile;
	}

	public String getGcpSettingFile() {
		return gcpSettingFile;
	}

	public void setGcpSettingFile(String gcpSettingFile) {
		this.gcpSettingFile = gcpSettingFile;
	}

	public String getUserFile() {
		return userFile;
	}

	public void setUserFile(String userFile) {
		this.userFile = userFile;
	}

	

	public String getUploadDirectory() {
		return uploadDirectory;
	}

	public void setUploadDirectory(String uploadDirectory) {
		this.uploadDirectory = uploadDirectory;
	}

}