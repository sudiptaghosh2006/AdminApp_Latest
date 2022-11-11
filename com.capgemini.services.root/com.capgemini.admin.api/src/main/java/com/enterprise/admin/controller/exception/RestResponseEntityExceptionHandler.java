package com.enterprise.admin.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.capgemini.common.dto.ApplicationResponseData;
import com.capgemini.common.dto.ServiceResponseStatus;
import com.capgemini.common.dto.ServiceResponseStatusConstant;
import com.capgemini.common.exception.ApplicationCommonException;
import com.enterprise.services.model.ReaourceConfiguration;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{


    @ExceptionHandler(value ={ ApplicationCommonException.class })
    protected ResponseEntity<ApplicationResponseData<ReaourceConfiguration>> handleUserValidationException(ApplicationCommonException ex, WebRequest request)
    {

	ServiceResponseStatus responseStatus = new ServiceResponseStatus();
	responseStatus.setResponseCode(ServiceResponseStatusConstant.ERROR_CODE);
	responseStatus.setResponseMessage(ServiceResponseStatusConstant.ERROR_MESSAGE);
	responseStatus.addDetailMessage(ex.getMessage());
	ApplicationResponseData<ReaourceConfiguration> responseData=new ApplicationResponseData<>();
	responseData.setResponseStatus(responseStatus);

	return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}