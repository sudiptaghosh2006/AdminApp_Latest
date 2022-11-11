package com.enterprise.admin.login;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.common.dto.ApplicationResponseData;
import com.capgemini.common.dto.ServiceResponseStatus;
import com.capgemini.common.dto.ServiceResponseStatusConstant;
import com.capgemini.common.dto.UserValidationResult;
import com.enterprise.admin.file.IApplicationFileHandler;
import com.enterprise.admin.utilities.DefaultUserProperty;
import com.enterprise.admin.utilities.DocumentStorageProperty;
import com.enterprise.services.model.UserDTO;


@Service
public class LoginServiceImpl implements ILoginService {
		
	@Autowired
	private IApplicationFileHandler<UserDTO> fileHandler;
	private Path docStorageLocation;
	private DefaultUserProperty userProperty;
	private DocumentStorageProperty documentStorageProperty;
	
	
	@Autowired
	public LoginServiceImpl(DocumentStorageProperty documentStorageProperty,DefaultUserProperty userProperty) throws IOException {
		
		this.userProperty=userProperty;
		this.documentStorageProperty=documentStorageProperty;
		this.docStorageLocation = Paths.get(documentStorageProperty.getUploadDirectory()).toAbsolutePath().normalize();
	}
	
	@Override
	public ApplicationResponseData<UserValidationResult> validateUser(UserDTO userDTO) 
	{
//		
		ApplicationResponseData<UserValidationResult> responseData=new ApplicationResponseData<>();
		UserValidationResult userValidationResult=new UserValidationResult();
		userValidationResult.setUser(userDTO.getUser()).setValidUser(false).setDefaultUser(false);
		UserDTO userDTOFromFile = fileHandler.deDerializeFromFile(docStorageLocation, documentStorageProperty.getUserFile());
		if(userDTOFromFile !=null)
		{
			System.out.println(" validating User  against file ");
			if(userDTO.getUser().equals(userDTOFromFile.getUser()) && userDTO.getPassword().equals(userDTOFromFile.getPassword()) )
			{
				userValidationResult.setValidUser(true);
				
			}
		}
		else
		{
			System.out.println(" validating User  using default values ");
			if(userDTO.getUser().equals(userProperty.getUser()) && userDTO.getPassword().equals(userProperty.getPassword()) )
			{
				userValidationResult.setUser(userDTO.getUser()).setDefaultUser(true).setValidUser(true);
			}
		}
		responseData.setResponseData(userValidationResult);
		
		
		 ServiceResponseStatus responseStatus=new ServiceResponseStatus();
		 responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
		 responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
		 responseData.setResponseStatus(responseStatus);
		return responseData;
	}

	@Override
	public Boolean saveLoginDetail(UserDTO userDTO) {

		return fileHandler.serializeToFile(userDTO,docStorageLocation, documentStorageProperty.getUserFile());
		
	}

}
