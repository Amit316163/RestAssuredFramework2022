package com.qa.gorest.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.utils.APIHttpStatusCode;
import com.qa.gorest.utils.JsonPathValidator;

import io.restassured.response.Response;

public class FakeStore extends BaseTest{
	
	@BeforeMethod
	public void getUserSetup() {
		 restClient=new RestClient(prop, baseURI);
	}
	
	
	@Test
	public void getAllUsersTest() {
		Response response=restClient.doGet(FAKESTORE_ENDPOINT,false, true);
		
		Assert.assertEquals(response.getStatusCode(), APIHttpStatusCode.OK_200.getCode());
		
		JsonPathValidator js=new JsonPathValidator();
		
		List<Float> ob=js.readList(response, "$[?(@.rating.rate<3)].rating.rate");
		System.out.println(ob);
		
		
				List<Map<String,Object>> jwseleryList=js.readListOfMaps(response, "$[?(@.category=='jewelery')].[\"title\",\"price\"]");
				System.out.println(jwseleryList);
				
				for(Map<String,Object> products : jwseleryList) {
					
					String title=(String) products.get("title");
					Object prices=products.get("price");
					System.out.println("title "+title);
					System.out.println("price "+prices);
					System.out.println("=======================");
				}
		
		
	}

}
