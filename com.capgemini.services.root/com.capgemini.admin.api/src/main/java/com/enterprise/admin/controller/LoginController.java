package com.enterprise.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.common.dto.ApplicationResponseData;
import com.capgemini.common.dto.UserValidationResult;
import com.enterprise.admin.login.ILoginService;
import com.enterprise.services.model.UserDTO;

@RestController

@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private ILoginService loginService ;
	
	@PostMapping("/saveUser")
	public Boolean saveUser(@RequestBody UserDTO userDTO)  
	{		
		return loginService.saveLoginDetail(userDTO);
		
	}
	
	@PostMapping("/validateUser")
	public ApplicationResponseData<UserValidationResult> validateUser(@RequestBody UserDTO userDTO)  
	{		
		return loginService.validateUser(userDTO);
		
	}
	
}
