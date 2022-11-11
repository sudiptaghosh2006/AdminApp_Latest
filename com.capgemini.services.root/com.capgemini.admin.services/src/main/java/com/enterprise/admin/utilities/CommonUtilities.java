package com.enterprise.admin.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtilities {

	
	
	private static final String UNIQUE_STRING_PATTERN= "yyyyMMddHHmmssSS";

	private CommonUtilities() {
		super();
		
	}

	public static synchronized String getUniqueStringTime()
	{
	    
	        Date dNow = new Date();
	        SimpleDateFormat ft = new SimpleDateFormat(UNIQUE_STRING_PATTERN);
	        String datetime = ft.format(dNow);
	        try
	        {
	            Thread.sleep(1);
	        }catch(Exception e)
	        {
	            
	        }
	        return datetime;

	}
}
