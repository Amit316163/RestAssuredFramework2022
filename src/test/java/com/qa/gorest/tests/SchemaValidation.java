package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.APIConstant;
import com.qa.gorest.utils.APIHttpStatusCode;
import com.qa.gorest.utils.ExcelUtils;
import com.qa.gorest.utils.StringUtils;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.IOException;



public class SchemaValidation extends BaseTest {
	
	
	@BeforeMethod
	public void createUserSetup() {
		 restClient=new RestClient(prop, baseURI);
	}
	
	
	@Test
	public void createUserSchemaTest() {
		
		User user=new User("Raj","male",StringUtils.randomEmailID(),"active");
		
	restClient.doPost(GOREST_ENDPOINT, "json", user,true, true)
	.then().log().all().assertThat().statusCode(APIHttpStatusCode.CREATED_201.getCode())
	.body(matchesJsonSchemaInClasspath("NewUserSchemaFile.json"));
	
		
		
	}
	@Test
	public void getUserSchemaTest() {
		
		restClient.doGet("/public/v2/users", false, true)
		.then().log().all().statusCode(APIHttpStatusCode.OK_200.getCode())
		.assertThat().body(matchesJsonSchemaInClasspath("GETALLSchemaFile.json"));

}
}