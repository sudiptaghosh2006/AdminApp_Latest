package com.capgemini.common.dto;

import java.util.ArrayList;
import java.util.List;

public class ServiceResponseStatus
{

    private int responseCode;
    private String responseMessage;
    private List<String> detailMessageList;

    public List<String> getDetailMessageList()
    {
	return detailMessageList;
    }

    public ServiceResponseStatus setDetailMessageList(List<String> detailMessageList)
    {
	this.detailMessageList = detailMessageList;
	return this;
    }

    public ServiceResponseStatus addDetailMessage(String detailMessage)
    {
	if (detailMessageList == null)
	{
	    detailMessageList = new ArrayList<>();
	}
	detailMessageList.add(detailMessage);
	return this;
    }

    public int getResponseCode()
    {
	return responseCode;
    }

    public ServiceResponseStatus setResponseCode(int responseCode)
    {
	this.responseCode = responseCode;
	return this;
    }

    public String getResponseMessage()
    {
	return responseMessage;
    }

    public ServiceResponseStatus setResponseMessage(String responseMessage)
    {
	this.responseMessage = responseMessage;
	return this;
    }

}
