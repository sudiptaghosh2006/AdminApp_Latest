package com.enterprise.admin.git;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.enterprise.admin.utilities.DocumentStorageProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class GitDownloadDocumentServices implements IGitService {
	

	@Autowired
	private DocumentStorageProperty documentStorageProperty;
	
	
	
	
	

	@Override
	public void downloadDocument() throws IOException, URISyntaxException{

	
		RestTemplate restTemplate = new RestTemplate();
			
			String username = "sudipta-at-382869703553";
			String password = "CGxBLP2xzECzH4btNgZBx/hsDx9JnHsJ2EWjqwGMhPA=";
			
		    HttpHeaders headers = new HttpHeaders();
		    headers.setBasicAuth(username, password);
		    HttpEntity<String> request = new HttpEntity<String>(headers);
		    
		    
//		    https://git-codecommit.us-west-2.amazonaws.com/v1/repos/EUREKA_MICROSERVICE_DEMO
		   ResponseEntity<List> exchange = restTemplate.exchange("https://git-codecommit.us-west-2.amazonaws.com/v1/repos/EUREKA_MICROSERVICE_DEMO",HttpMethod.GET,
						request,List.class);
			    
		    
		    List<Map> response = exchange.getBody();
		


		// To print response JSON, using GSON. Any other JSON parser can be used here.
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println("<JSON RESPONSE START>\n" + gson.toJson(response) + "\n<JSON RESPONSE END>\n");

		// Iterate through list of file metadata from response.
		for (Map fileMetaData : response) {

			// Get file name & raw file download URL from response.
			String fileName = (String) fileMetaData.get("name");
			String downloadUrl = (String) fileMetaData.get("download_url");
			System.out.println("File Name = " + fileName + " | Download URL = " + downloadUrl);

			// We will only fetch read me file for this example.
			if (downloadUrl != null && downloadUrl.contains("pom")) {

				/*
				 * Get file content as string
				 * 
				 * Using Apache commons IO to read content from the remote URL. Any other HTTP
				 * client library can be used here.
				 */
				String fileContent = IOUtils.toString(new URI(downloadUrl), Charset.defaultCharset());
				System.out.println("\nfileContent = <FILE CONTENT START>\n" + fileContent + "\n<FILE CONTENT END>\n");

				/*
				 * Download read me file to local.
				 * 
				 * Using Apache commons IO to create file from remote content. Any other library
				 * or code can be written to get content from URL & create file in local.
				 */

				
				Path docStorageLocation = Paths.get(documentStorageProperty.getUploadDirectory()).toAbsolutePath().normalize();
				
				Path finalPath = Paths.get(docStorageLocation.toString(), fileName);
				FileUtils.copyURLToFile(new URL(downloadUrl), finalPath.toFile());
			}
		}
	}
}
