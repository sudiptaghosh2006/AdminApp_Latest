package com.enterprise.admin.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.common.dto.ApplicationResponseData;
import com.capgemini.common.dto.ServiceResponseStatus;
import com.capgemini.common.dto.ServiceResponseStatusConstant;
import com.enterprise.admin.git.IGitService;
import com.enterprise.services.model.ReaourceConfiguration;

@RestController
@RequestMapping("/api/git")
public class GitServiceController {
	
	@Autowired
	IGitService gitService;
	
	
	
	@GetMapping("/download")
	public ApplicationResponseData<ServiceResponseStatus> getDocument() {
		
		ApplicationResponseData<ServiceResponseStatus> responseData=new ApplicationResponseData<>();
		try {
			ServiceResponseStatus responseStatus=new ServiceResponseStatus();
			responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
			responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
			gitService.downloadDocument();
			responseData.setResponseStatus(responseStatus);
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return responseData;
	}
	

}
