package com.enterprise.admin.document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.common.dto.ApplicationResponseData;
import com.capgemini.common.dto.ServiceResponseStatus;
import com.capgemini.common.dto.ServiceResponseStatusConstant;
import com.capgemini.common.exception.ApplicationCommonException;
import com.capgemini.terraform.parser.TerraformParser;
import com.enterprise.admin.file.IApplicationFileHandler;
import com.enterprise.admin.utilities.DocumentStorageProperty;
import com.enterprise.admin.utilities.FileDetails;
import com.google.gson.Gson;

@Service
public class DocumentServiceImpl<T> implements IDocumentService<T> {

	@Autowired
	private IApplicationFileHandler<T> applicationFileHandler;
	private final Path docStorageLocation;
	
	private DocumentStorageProperty documentStorageProperty;
	
	@Autowired
	public DocumentServiceImpl(DocumentStorageProperty documentStorageProperty) throws IOException {
		this.docStorageLocation = Paths.get(documentStorageProperty.getUploadDirectory()).toAbsolutePath().normalize();
		this.documentStorageProperty=documentStorageProperty;
		Files.createDirectories(this.docStorageLocation);
	}

	@Override
	public void saveDocuments(T t,String fileNamePattern) 
	{			
		applicationFileHandler.serializeToFile(t,docStorageLocation, fileNamePattern);
	}

	@Override
	public ApplicationResponseData<T> getDocuments(String fileName)  {
				
		 T documents = applicationFileHandler.deDerializeFromFile(docStorageLocation, fileName);
		 if (documents == null) 
		 {
				throw new ApplicationCommonException("Document Not Found for file   :::    " + fileName);
		 }
		 ApplicationResponseData<T> responseData=new ApplicationResponseData<>();
		 responseData.setResponseData(documents);
		 ServiceResponseStatus responseStatus=new ServiceResponseStatus();
		 responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
		 responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
		 
		 responseData.setResponseStatus(responseStatus);
		 return responseData;
		
		 
	}
	
	public ApplicationResponseData<Boolean> deleteDocuments(String fileName) 
	{
		boolean deleteFile = applicationFileHandler.deleteFile(docStorageLocation, fileName);
		
		 ApplicationResponseData<Boolean> responseData=new ApplicationResponseData<>();
		 responseData.setResponseData(deleteFile);
		 ServiceResponseStatus responseStatus=new ServiceResponseStatus();
		 responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
		 responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
		 
		 responseData.setResponseStatus(responseStatus);
		 return responseData;
		
	}

	@Override
	public byte[] downloadDocument(String fileName) {
		ApplicationResponseData<T> documents = getDocuments(fileName);
		Gson gson = new Gson();
		String documentJson = gson.toJson(documents);
		return documentJson.getBytes();
		
	}
	
	public ApplicationResponseData<Map<String, Object>>  getTerraformDetails(String fileName)  {
		
		 Map<String, Object> map = TerraformParser.parseFile(fileName);
		 ApplicationResponseData<Map<String, Object>> responseData=new ApplicationResponseData<>();
		 responseData.setResponseData(map);
		 ServiceResponseStatus responseStatus=new ServiceResponseStatus();
		 responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
		 responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
		 
		 responseData.setResponseStatus(responseStatus);
		 return responseData;
		
		 
	}
	
	public ApplicationResponseData<List<FileDetails>> listFiles(Path docStorageLocation)
	{
		
		 ApplicationResponseData<List<FileDetails>> responseData=new ApplicationResponseData<>();
		 
		 ServiceResponseStatus responseStatus=new ServiceResponseStatus();
		 responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
		 responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
		 
		 responseData.setResponseStatus(responseStatus);
		 responseData.setResponseData(applicationFileHandler.listFiles(docStorageLocation));
		 
		 return responseData;
		
		
	}

	public ApplicationResponseData<List<String>> listProviders()
	{
		
		 ApplicationResponseData<List<String>> responseData=new ApplicationResponseData<>();
		 
		 ServiceResponseStatus responseStatus=new ServiceResponseStatus();
		 responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
		 responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
		 
		 responseData.setResponseStatus(responseStatus);
		 String providerList = documentStorageProperty.getCloudProviderList();
		 
		 
		 responseData.setResponseData((Arrays.asList(providerList.split(","))));
		 
		 return responseData;
		
		
	}
	
	public ApplicationResponseData<List<String>> listVersions()
	{
		
		 ApplicationResponseData<List<String>> responseData=new ApplicationResponseData<>();
		 
		 ServiceResponseStatus responseStatus=new ServiceResponseStatus();
		 responseStatus.setResponseCode(ServiceResponseStatusConstant.SUCCESS_CODE);
		 responseStatus.setResponseMessage(ServiceResponseStatusConstant.SUCCESS_MESSAGE);
		 
		 responseData.setResponseStatus(responseStatus);
		 String providerList = documentStorageProperty.getAvailableVersions();
		 
		 
		 responseData.setResponseData((Arrays.asList(providerList.split(","))));
		 
		 return responseData;
		
		
	}
	
}
