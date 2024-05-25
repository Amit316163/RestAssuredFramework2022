package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.utils.APIHttpStatusCode;

public class ReqRes extends BaseTest{
	
	@BeforeMethod
	public void getUserSetup() {
		 restClient=new RestClient(prop, baseURI);
	}
	
	
	@Test
	public void getAllUsersTest() {
		restClient.doGet(RESRES_ENDPOINT, false,true).
		then().assertThat().statusCode(APIHttpStatusCode.OK_200.getCode()).log().all();
		
	}

}
