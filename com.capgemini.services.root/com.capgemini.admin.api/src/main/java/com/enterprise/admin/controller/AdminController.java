package com.enterprise.admin.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.admin.IAdminService;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private IAdminService adminService;

	@GetMapping("/jenkins")
	public void invokeJenkinsJob() throws IOException, InterruptedException {
		String consoleLog = adminService.invokeJenkinsJob();
//		adminService.parsingConsoleLog(consoleLog);
	}


}
