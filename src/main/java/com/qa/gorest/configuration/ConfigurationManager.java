package com.qa.gorest.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.gorest.frameworkexception.APIFrameworkException;

public class ConfigurationManager {
	
	private Properties prop;
	private FileInputStream fis;
	
	
	
	public Properties initProp() {
	
		prop=new Properties();
		
		String envName=System.getProperty("env");
		try {
		if(envName==null) {
			System.out.println("no env is given hence running test cases on QA env");
			fis=new FileInputStream("./src/test/resources/config/qa.config.properties");
		}else {
			System.out.println("Running test on env==> "+envName);
			switch (envName.toLowerCase().trim()) {
			case "dev":
				
					fis=new FileInputStream("./src/test/resources/config/dev.config.properties");
				
				 break;
			case "qa":
				fis=new FileInputStream("./src/test/resources/config/qa.config.properties");
				 break;
			case "stage":
				fis=new FileInputStream("./src/test/resources/config/stage.config.properties");
				 break;
				
			default:
				System.out.println("Please provide the right env path.."+envName);
				throw new APIFrameworkException("Unexpected value: " + envName);
				
			}	}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		
		}	
		
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
		
	}
}
		
//		prop=new Properties();
//		try {
//			fis=new FileInputStream("./src/test/resources/config/config.properties");
//			try {
//				prop.load(fis);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		return prop;
//	}

//}
