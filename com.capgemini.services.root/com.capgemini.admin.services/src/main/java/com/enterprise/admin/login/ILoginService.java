package com.enterprise.admin.login;

import com.capgemini.common.dto.ApplicationResponseData;
import com.capgemini.common.dto.UserValidationResult;
import com.enterprise.services.model.UserDTO;

public interface ILoginService {
	
	public ApplicationResponseData<UserValidationResult>validateUser(UserDTO userDTO);
	public Boolean saveLoginDetail(UserDTO userDTO);

}
