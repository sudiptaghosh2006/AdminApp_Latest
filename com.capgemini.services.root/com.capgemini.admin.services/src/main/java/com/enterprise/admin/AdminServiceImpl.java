package com.enterprise.admin;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.admin.utilities.JenkinsProperty;




@Service
public class AdminServiceImpl implements IAdminService {
	
	private JenkinsProperty jenkinsProperty;
	
	@Autowired
	public AdminServiceImpl(JenkinsProperty jenkinsProperty) throws IOException {
		this.jenkinsProperty = jenkinsProperty;
	
	}
	

	@Override
	public String invokeJenkinsJob() throws IOException, InterruptedException {
		
		String user = jenkinsProperty.getUser(); 
	    String pass = jenkinsProperty.getPassword() ;
		
//		String user = "jenkins-user"; // username
//	    String pass = "11657f0d1d75312eafb9393d91d68d8e36"; //  API token
	    String plainCredentials = user + ":" + pass;
	    String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
        // Create authorization header
        String authorizationHeader = "Basic " + base64Credentials;

        HttpClient client = HttpClient.newHttpClient();
        
        //Running Jenkins Job
        // Create HTTP request object
        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://13.82.139.160:8080/job/terraform-example/build"))
                .uri(URI.create(jenkinsProperty.getUrl()))
                .POST(BodyPublishers.ofString("token=11657f0d1d75312eafb9393d91d68d8e36"))
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        // Send HTTP request
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.toString());
        
        return response.body();
        
       /* request = HttpRequest.newBuilder()
                .uri(URI.create("http://13.82.139.160:8080/job/Sample-Terraformjob/lastBuild/api/json"))
                .GET()
                .header("Authorization", authorizationHeader)
                .build();*/
        
        //Getting Status of Jenkins Job from Console Log
//        request = HttpRequest.newBuilder()
//                .uri(URI.create("http://13.82.139.160:8080/job/terraform-example/67/consoleText"))
//                .GET()
//                .header("Authorization", authorizationHeader)
//                .build();
//        
//        HttpResponse<String> respons = client.send(request,
//                HttpResponse.BodyHandlers.ofString());
        
//        return respons.body();
	}

	

}
