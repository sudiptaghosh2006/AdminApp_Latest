package com.enterprise.admin.document;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import com.capgemini.common.dto.ApplicationResponseData;
import com.enterprise.admin.utilities.FileDetails;

public interface IDocumentService <T>{
	
	public void saveDocuments(T t,String fileName) ;
	
	public ApplicationResponseData<T> getDocuments(String fileName) ;
	
	public byte[] downloadDocument(String fileName);
	
	public ApplicationResponseData<Map<String, Object>>  getTerraformDetails(String fileName) ;
	
	public ApplicationResponseData<List<FileDetails>> listFiles(Path docStorageLocation) ;
	
	public ApplicationResponseData<List<String>> listProviders();
	
	public ApplicationResponseData<List<String>> listVersions();
	
	public ApplicationResponseData<Boolean> deleteDocuments(String fileName) ;
}
