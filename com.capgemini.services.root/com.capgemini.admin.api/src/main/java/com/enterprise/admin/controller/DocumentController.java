package com.enterprise.admin.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.common.dto.ApplicationResponseData;
import com.enterprise.admin.document.IDocumentService;
import com.enterprise.admin.utilities.CommonUtilities;
import com.enterprise.admin.utilities.DocumentStorageProperty;
import com.enterprise.admin.utilities.FileDetails;
import com.enterprise.services.model.ReaourceConfiguration;

@RestController
@RequestMapping("/api/configuration")
public class DocumentController {

	
	
	private static final String STRING_UNDERSCORE="_";

	private static final String GCP = "GCP";

	private static final String AZURE = "AZURE";

	private static final String AWS = "AWS";

	@Autowired
	private IDocumentService<ReaourceConfiguration> documentService;
	
	@Autowired
	private DocumentStorageProperty documentStorageProperty ;
	
	@PostMapping("/saveConfiguration")
	public void saveAwsDocument(@RequestBody ReaourceConfiguration document) {

		String cloudProvider = document.getCloudProvider();
		String systemVersion = document.getSystemVersion();
		String fileNamePattern="";
		if(cloudProvider.equalsIgnoreCase(AWS))
		{
			fileNamePattern=documentStorageProperty.getAwsSettingFile();
		}
		else if(cloudProvider.equalsIgnoreCase(AZURE))
		{
			fileNamePattern=documentStorageProperty.getAzureSettingFile();
		}
		else if(cloudProvider.equalsIgnoreCase(GCP))
		{
			fileNamePattern=documentStorageProperty.getGcpSettingFile();
		}
		
		String finalFileName=fileNamePattern+STRING_UNDERSCORE+systemVersion
				+STRING_UNDERSCORE+CommonUtilities.getUniqueStringTime()
				+documentStorageProperty.getSettingFileExtension();
		documentService.saveDocuments(document,finalFileName);
	
	}
	
	
	@GetMapping("/getAvailableConfigurations")
	public ApplicationResponseData<List<FileDetails>> getAllDocument()
	{
		Path path = Paths.get(documentStorageProperty.getUploadDirectory()).toAbsolutePath().normalize();
		return documentService.listFiles(path);		
	}
	
	@GetMapping("/deleteConfiguration")
	public ApplicationResponseData<Boolean> deleteDocument(@RequestParam String fileName)
	{
		return documentService.deleteDocuments(fileName);		
	}
	
	@GetMapping("/getConfiguration")
	public ApplicationResponseData<ReaourceConfiguration> getDocument(@RequestParam String fileName)
	{
		return documentService.getDocuments(fileName);		
	}
	
	@GetMapping("/downloadConfiguration")
	public ResponseEntity<byte[]> downloadDocument(@RequestParam String fileName) {
		byte[] documentJsonBytes = documentService.downloadDocument(fileName);
		String headerValue = "attachment; filename=\"" + fileName + ".json\"";
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
				.contentType(MediaType.APPLICATION_JSON)
				.contentLength(documentJsonBytes.length)
				.body(documentJsonBytes);
		
	}
	
	@GetMapping("/getTerraformDetails")
	public ApplicationResponseData<Map<String, Object>> getTerraformDetails(@RequestParam String fileName) {
//		return documentService.getTerraformDetails("variable1.tf");

		return documentService.getTerraformDetails(fileName);
		
		
	}
	
	@GetMapping("/getCloudProviders")
	public ApplicationResponseData<List<String>>  getCloudProviders() {
		return documentService.listProviders();		
	}
	
	@GetMapping("/getApplicationVersions")
	public ApplicationResponseData<List<String>>  getApplicationVersions() {
		return documentService.listVersions();		
	}
	
	
}