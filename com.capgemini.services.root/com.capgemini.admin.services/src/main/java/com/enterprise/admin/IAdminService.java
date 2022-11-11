package com.enterprise.admin;

import java.io.IOException;


public interface IAdminService {
	String invokeJenkinsJob() throws IOException, InterruptedException;
}
