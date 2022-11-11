package com.enterprise.admin.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.common.exception.ApplicationCommonException;
import com.enterprise.admin.utilities.FileDetails;

@Service
public class ApplicationFileHandler<T> implements IApplicationFileHandler<T> {

	private List<String> collect;

	@Override
	public boolean serializeToFile(Object object, Path docStorageLocation, String fileName) {
		boolean returnValue = false;

		Path finalPath = Paths.get(docStorageLocation.toString(), fileName);

		try (ObjectOutputStream outStream = new ObjectOutputStream(Files.newOutputStream(finalPath))) {
			outStream.writeObject(object);
			returnValue = true;
		} catch (IOException i) {
			i.printStackTrace();
		}

		return returnValue;

	}
	
	public boolean deleteFile( Path docStorageLocation, String fileName) 
	{
		boolean returnValue=false;
		Path finalPath = Paths.get(docStorageLocation.toString(), fileName);
		try {
			boolean exists = Files.exists(finalPath);
			if(exists)
			{
				returnValue=  Files.deleteIfExists(finalPath);
			}
			else {
				throw new ApplicationCommonException(fileName +"  Not found ");
			}
		} catch (Exception e) 
		{
			
			throw new ApplicationCommonException(e.getMessage());
		}
		
		return returnValue;

	}

	@Override
	public <T> T deDerializeFromFile(Path docStorageLocation, String fileName) {
		Path path = Paths.get(docStorageLocation.toString(), fileName);
		T t = null;
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path.toString()))) {
			t = (T) in.readObject();
			System.out.println("Object has been deserialized ");

		} catch (Exception e) {
			System.out.println("File Not Found "+e.getMessage());
		} 

		return t;
	}

	public List<FileDetails> listFiles(Path docStorageLocation) 
	{
		List<FileDetails> listFileDetails=new ArrayList<>();
		try {
			List<Path> listFilePath = Files.walk(docStorageLocation).filter(path->path.toFile().isFile()).toList();
			for (Path path : listFilePath) 
			{
				
				FileDetails fileDetails=new FileDetails();
				fileDetails.setFileName(path.getFileName().toString());
				
				System.out.println("########################################################### ");
				BasicFileAttributeView basicView = 
						  Files.getFileAttributeView(path, BasicFileAttributeView.class);
				
				
				
				BasicFileAttributes basicFileAttributes = basicView.readAttributes();
			
				System.out.println("file Name "+path.getFileName().toString());
				System.out.println("creattion time "+basicFileAttributes.creationTime());
				System.out.println("Access "+basicFileAttributes.lastAccessTime().toInstant());
				System.out.println("size"+basicFileAttributes.size());
//				
				
				FileOwnerAttributeView ownerView = Files.getFileAttributeView(
						path, FileOwnerAttributeView.class);
				
				System.out.println("Owner "+ownerView.getOwner().getName());
				
				fileDetails.setCreationTime(basicFileAttributes.creationTime().toString());
				fileDetails.setLastAccessedTime(basicFileAttributes.lastAccessTime().toString());
				fileDetails.setSize(basicFileAttributes.size());
				fileDetails.setOwener(ownerView.getOwner().getName());
				
				System.out.println("########################################################### ");
				
				listFileDetails.add(fileDetails);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listFileDetails;
	}
}
