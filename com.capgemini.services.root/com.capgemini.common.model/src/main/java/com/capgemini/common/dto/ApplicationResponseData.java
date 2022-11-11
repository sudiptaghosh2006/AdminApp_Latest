package com.capgemini.common.dto;

public class ApplicationResponseData<T>
{
    private ServiceResponseStatus responseStatus;

   
    private T responseData;

    public T getResponseData() {
		return responseData;
	}

	public void setResponseData(T responseData) {
		this.responseData = responseData;
	}

	public ServiceResponseStatus getResponseStatus()
    {
	return responseStatus;
    }

    public ApplicationResponseData<T> setResponseStatus(ServiceResponseStatus responseStatus)
    {
    	this.responseStatus = responseStatus;
    	return this;
    }

}
