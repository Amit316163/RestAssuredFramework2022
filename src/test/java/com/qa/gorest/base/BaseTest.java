package com.qa.gorest.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configuration.ConfigurationManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	
	
	//Service url
	public static final String GOREST_ENDPOINT="/public/v2/users";
	public static final String FAKESTORE_ENDPOINT="/products";
	public static final String RESRES_ENDPOINT="/api/users";
	
	protected ConfigurationManager config;
	protected Properties prop;
	protected RestClient restClient;
	protected String baseURI;
	
	@Parameters({"baseURI"})
	@BeforeTest
	public void setUp(String baseURI) {
		
		RestAssured.filters(new AllureRestAssured());
		
		 config=new ConfigurationManager();
		 
		 prop=config.initProp();
		
		 this.baseURI=baseURI;
		//String baseURI=prop.getProperty("baseURI");
		//restClient=new RestClient(prop, baseURI);
		
	}

}
