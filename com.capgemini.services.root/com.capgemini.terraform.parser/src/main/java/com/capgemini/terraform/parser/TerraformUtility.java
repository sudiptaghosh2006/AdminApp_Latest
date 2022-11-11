package com.capgemini.terraform.parser;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import com.bertramlabs.plugins.hcl4j.HCLParser;
import com.bertramlabs.plugins.hcl4j.HCLParserException;

public class TerraformUtility {

	private TerraformUtility() {}
	public static Map<String, Object> parseFile(String filename)
	{
		Map<String, Object> outputMap=null;
		
		File terraformFile=null;
		try {
			terraformFile = getFileFromResource(filename);
		} catch (URISyntaxException e1) {
		
			e1.printStackTrace();
		}
		
		HCLParser hclParser = new HCLParser();
		try {
			outputMap= hclParser.parse(terraformFile, "UTF-8");			
			System.out.println(outputMap);
		} catch (HCLParserException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return outputMap;
	}
	
	
	private static File getFileFromResource(String fileName) throws URISyntaxException{

        ClassLoader classLoader = TerraformUtility.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }

    }
	
	

}
