package com.capgemini.terraform.parser;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import com.bertramlabs.plugins.hcl4j.HCLParser;
import com.bertramlabs.plugins.hcl4j.HCLParserException;

public class TerraformParser {

	private TerraformParser() {}
	
//	public static Map<String, Object>  parseFile(String filename)
	public static <T> T parseFile(String filename)
	{
		T returnMap=null;
		
		File terraformFile=null;
		try {
			terraformFile = getFileFromResource(filename);
		} catch (URISyntaxException e1) {
		
			e1.printStackTrace();
		}
		
		HCLParser hclParser = new HCLParser();
		try {
			returnMap = (T) hclParser.parse(terraformFile, "UTF-8");			
			System.out.println(returnMap);
		} catch (HCLParserException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return returnMap;
	}
	
	
	private static File getFileFromResource(String fileName) throws URISyntaxException{

        ClassLoader classLoader = TerraformParser.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }

    }
	
	public static void main(String[] args) {
		new TerraformParser().parseFile("variable1.tf");
	}
	
}
