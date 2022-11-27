package com.freecrm.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	

	private Properties properties;
	
	public   Configuration() {
		String configfilepath=System.getProperty("user.dir")+"/src/test/resources/config.properties";
		try {
			FileInputStream input=new FileInputStream(configfilepath);
			properties=new Properties();
			properties.load(input);
					}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public String getvalue(String key) {
		return properties.getProperty(key);
	}

}
