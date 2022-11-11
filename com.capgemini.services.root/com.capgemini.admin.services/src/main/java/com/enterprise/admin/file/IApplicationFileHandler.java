package com.enterprise.admin.file;

import java.nio.file.Path;
import java.util.List;

import com.enterprise.admin.utilities.FileDetails;

public interface  IApplicationFileHandler<T>
{
	
	public boolean serializeToFile(Object object,Path docStorageLocation,String fileName);
	
	public boolean deleteFile( Path docStorageLocation, String fileName);
	
	public <T> T deDerializeFromFile(Path docStorageLocation,String fileName);
	
	public List<FileDetails> listFiles(Path docStorageLocation);


}
