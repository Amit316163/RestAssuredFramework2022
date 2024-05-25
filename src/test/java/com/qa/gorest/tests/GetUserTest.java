package com.qa.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.utils.APIHttpStatusCode;
import com.qa.gorest.utils.JsonPathValidator;

import groovyjarjarantlr4.v4.runtime.atn.SemanticContext.AND;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetUserTest extends BaseTest {
	
	@BeforeMethod
	public void getUserSetup() {
		 restClient=new RestClient(prop, baseURI);
	}
	
	
	@Test(priority = 3)
	public void getAllUsersTest() {
		restClient.doGet(GOREST_ENDPOINT,true, true)
		.
		then().log().all().assertThat().statusCode(APIHttpStatusCode.OK_200.getCode()).log().all();
		
	}
	
	@Test(priority = 2)
	public void getUserTest() {
		restClient.doGet(GOREST_ENDPOINT+"/"+"6925940",true, true).
		then().assertThat().statusCode(APIHttpStatusCode.OK_200.getCode()).log().all().
		body("id", equalTo(6925940));
		
	}
	
	@Test(priority = 1)
	public void getUserQueryParamsTest() {
		Map<String,String> queryParams=new HashMap<String,String>();
		
		queryParams.put("name", "Mayur jain");
		queryParams.put("gender", "male");
		restClient.doGet(GOREST_ENDPOINT, null, queryParams,true, true).
		then().assertThat().statusCode(APIHttpStatusCode.OK_200.getCode()).log().all();
	
	}
	
	
//	@Test()
//	public void getAllUsersTestValidator() {
//		Response response=restClient.doGet(GOREST_ENDPOINT+"/"+6903812,true, true);
//		int statusCode=response.getStatusCode();
//		Assert.assertEquals(statusCode, APIHttpStatusCode.OK_200.getCode());
//
//	}
}
