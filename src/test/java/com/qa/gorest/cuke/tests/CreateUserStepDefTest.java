package com.qa.gorest.cuke.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.User;

import com.qa.gorest.utils.StringUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;




public class CreateUserStepDefTest extends BaseTest {
	
	User user;
	Response response;
	
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

//	@Given("Perform a Post call for given test data")
//	public void perform_a_Post_call_for_given_test_data(String name,String gender,String status) {
//		 user=new User(name,gender,StringUtils.randomEmailID(),status);
//	}
//
//	@When("User is created successfully then print response body")
//	public void user_is_created_successfully_then_print_response_body() {
//		 response=restClient.doPost(GOREST_ENDPOINT, "json", user,true, true);
//	}
//
//	@Then("Validate the status code as {string}")
//	public void validate_the_status_code_as(String code) {
//		int statusCode=response.getStatusCode();
//			Assert.assertEquals(statusCode, code);
//	}
//
//	@Then("Validate name and status from response body")
//	public void validate_name_and_status_from_response_body() {
//		String responseBody=response.getBody().asString();
//		System.out.println(responseBody);
//	}
	
	@Given("Perform a Post call for given test data")
	public void perform_a_Post_call_for_given_test_data() {
	    System.out.println("given");
	}

	@When("User is created successfully then print response body")
	public void user_is_created_successfully_then_print_response_body() {
		System.out.println("when");
	}

	@Then("Validate the status code as {string}")
	public void validate_the_status_code_as(String string) {
		System.out.println("then");
	}

	@Then("Validate name and status from response body")
	public void validate_name_and_status_from_response_body() {
		System.out.println("then and then");
	}

	

}
