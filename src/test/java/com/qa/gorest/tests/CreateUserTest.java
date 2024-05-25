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

import java.io.IOException;



public class CreateUserTest extends BaseTest {
	
	ExcelUtils excel=new ExcelUtils();
	
	@BeforeMethod
	public void createUserSetup() {
		 restClient=new RestClient(prop, baseURI);
	}
	
	@DataProvider
	public Object[][] createUserData() {
		
		return new Object[][] {
			{"Rohit","male","active"},
			{"Sanu","female","active"},
			{"Sneha","female","inactive"},
		};
		
	}
	@DataProvider(name="excelData")
	public Object[][] createUserDataUsingExcel() throws IOException {
		
		return 	excel.excelReader(APIConstant.GOREST_USER_SHEET);
		
		
	}
	
	
	@Test(dataProvider = "createUserData")
	public void createUser(String name,String geneder,String status) {
		
		User user=new User(name,geneder,StringUtils.randomEmailID(),status);
		
		//post call
	Integer userId=	restClient.doPost(GOREST_ENDPOINT, "json", user,true, true)
	.then().log().all().assertThat().statusCode(APIHttpStatusCode.CREATED_201.getCode()).extract().path("id");
	
	System.out.println("user id==>"+userId);
	
	//GET call
//	RestClient restclientGet=new RestClient(prop, baseURI);
//	restclientGet.doGet(GOREST_ENDPOINT+"/"+userId+"", true,true)
//	.then().log().all().statusCode(APIHttpStatusCode.OK_200.getCode())
//	.assertThat().body("id", equalTo(userId));
		
		
	}

}
