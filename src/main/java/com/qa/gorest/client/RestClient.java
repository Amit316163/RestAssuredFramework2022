package com.qa.gorest.client;

import java.util.Map;
import java.util.Properties;

import com.qa.gorest.frameworkexception.APIFrameworkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

//	private static final String BASE_URI = "https://gorest.co.in";
//	private static final String TOKEN = "Bearer 2043b7426a320fb4bbe283fef008aabf2ea1163e989c984c9b02facbe7f29378";

	private static RequestSpecBuilder reqSpecBuilder;
	private Properties prop;
	private String baseURI;
	private boolean isAuthrizationHeaderAdded=false;

//	static {
//
//		reqSpecBuilder = new RequestSpecBuilder();
//	}
	
	public RestClient(Properties prop,String baseURI) {
		reqSpecBuilder = new RequestSpecBuilder();
		this.prop=prop;
		this.baseURI=baseURI;
	}

	// To add the token in header
	private void addAuthorizationHeader() {
		if(!isAuthrizationHeaderAdded) {
			reqSpecBuilder.addHeader("Authorization", prop.getProperty("token"));
			isAuthrizationHeaderAdded=true;
		}
		

	}
	
	//Different types of content types
	private void setRequestContentType(String contentType) {
		
		switch (contentType.toLowerCase()) {
		case "json": reqSpecBuilder.setContentType(ContentType.JSON) ;
			break;
		case "xml": reqSpecBuilder.setContentType(ContentType.XML) ;
		break;
		
		case "text": reqSpecBuilder.setContentType(ContentType.TEXT) ;
		break;
		default:
			System.out.println("Please provide the correct content type");
			throw new APIFrameworkException("Invalid Content Type");
		}
		

	}

	// Simple GET request where header have a token as method
	private RequestSpecification createRequestSpec(boolean tokenID) {
		reqSpecBuilder.setBaseUri(baseURI);
		if(tokenID) {
			addAuthorizationHeader();
		}
		return reqSpecBuilder.build();

	}

	//  GET request where multiple headers can be passed
	private RequestSpecification createRequestSpec(Map<String, String> headers,boolean tokenID) {
		reqSpecBuilder.setBaseUri(baseURI);
		if(tokenID) {
			addAuthorizationHeader();
		}
		if (headers != null) {
			reqSpecBuilder.addHeaders(headers);
		}
		return reqSpecBuilder.build();

	}

	//  GET request where multiple headers can be passed and Query Parameters
	private RequestSpecification createRequestSpec(Map<String, String> headers,Map<String, String> queryParams,boolean tokenID) {
		reqSpecBuilder.setBaseUri(baseURI);
		if(tokenID) {
			addAuthorizationHeader();
		}
		if (headers != null) {
			reqSpecBuilder.addHeaders(headers);
		}
		
		if (queryParams != null) {
			reqSpecBuilder.addQueryParams(queryParams);
		}
		return reqSpecBuilder.build();

	}
	
//  Post request where we can pass body and content type
	private RequestSpecification createRequestSpec(Object requestBody,String contentType,boolean tokenID) {
		reqSpecBuilder.setBaseUri(baseURI);
		if(tokenID) {
			addAuthorizationHeader();
		}
		setRequestContentType(contentType);
		if(requestBody!=null) {
		reqSpecBuilder.setBody(requestBody);
		}
		return reqSpecBuilder.build();

	}
	
//  Post request where we can pass body and content type along with headers
	private RequestSpecification createRequestSpec(Object requestBody,String contentType,Map<String, String> headers,boolean tokenID) {
		reqSpecBuilder.setBaseUri(baseURI);
		if(tokenID) {
			addAuthorizationHeader();
		}
		setRequestContentType(contentType);
		if(requestBody!=null) {
		reqSpecBuilder.setBody(requestBody);
		}
		if (headers != null) {
			reqSpecBuilder.addHeaders(headers);
		}
		
		return reqSpecBuilder.build();

	}
	
	
	//Get http methods--plain
	
	public Response doGet(String basePath,boolean tokenID,boolean log) {
		
		if(log) {
			
			return RestAssured.given(createRequestSpec(tokenID)).log().all()
			.when().get(basePath);
		}
		return RestAssured.given(createRequestSpec(tokenID))
				.when().get(basePath);
		
	}
	//Get http methods--with headers
public Response doGet(String basePath,Map<String,String>headers,boolean tokenID,boolean log) {
		
		if(log) {
			
			return RestAssured.given(createRequestSpec(headers,tokenID)).log().all()
			.when().get(basePath);
		}
		return RestAssured.given(createRequestSpec(headers,tokenID))
				.when().get(basePath);
		
	}

//Get http methods--with headers and query paramters
public Response doGet(String basePath,Map<String,String>headers,Map<String,String>queryParams,boolean tokenID,boolean log) {
	
	if(log) {
		
		return RestAssured.given(createRequestSpec(headers,queryParams,tokenID)).log().all()
		.when().get(basePath);
	}
	return RestAssured.given(createRequestSpec(headers,queryParams,tokenID))
			.when().get(basePath);
	
}


//Post http methods-- content type and body
public Response doPost(String basePath,String contentType,Object object,boolean tokenID,boolean log) {
	
	if(log) {
		
		return RestAssured.given(createRequestSpec(object, contentType,tokenID)).log().all()
		.when().post(basePath);
	}
	return RestAssured.given(createRequestSpec(object, contentType,tokenID))
			.when().post(basePath);
	
}
//Post http methods-- content type and body along with headers
public Response doPost(String basePath,String contentType,Object object,Map<String,String>headers,boolean tokenID,boolean log) {
	
	if(log) {
		
		return RestAssured.given(createRequestSpec(object, contentType, headers,tokenID)).log().all()
		.when().post(basePath);
	}
	return RestAssured.given(createRequestSpec(object, contentType, headers,tokenID)).log().all()
			.when().post(basePath);
	
}

//Put http methods-- content type and body
public Response doPut(String basePath,String contentType,Object object,boolean tokenID,boolean log) {
	
	if(log) {
		
		return RestAssured.given(createRequestSpec(object, contentType,tokenID)).log().all()
		.when().put(basePath);
	}

	return RestAssured.given(createRequestSpec(object, contentType,tokenID))
	.when().put(basePath);
	
}
//put http methods-- content type and body along with headers
public Response doPut(String basePath,String contentType,Object object,Map<String,String>headers,boolean tokenID,boolean log) {
	
	if(log) {
		
		return RestAssured.given(createRequestSpec(object, contentType, headers,tokenID)).log().all()
		.when().put(basePath);
	}
	return RestAssured.given(createRequestSpec(object, contentType, headers,tokenID)).log().all()
			.when().put(basePath);
	
}

//Patch http methods-- content type and body
public Response doPatch(String basePath,String contentType,Object object,boolean tokenID,boolean log) {
	
	if(log) {
		
		return RestAssured.given(createRequestSpec(object, contentType,tokenID)).log().all()
		.when().patch(basePath);
	}
	return RestAssured.given(createRequestSpec(object, contentType,tokenID))
			.when().patch(basePath);
	
}
//Patch http methods-- content type and body along with headers
public Response doPatch(String basePath,String contentType,Object object,Map<String,String>headers,boolean tokenID,boolean log) {
	
	if(log) {
		
		return RestAssured.given(createRequestSpec(object, contentType, headers,tokenID)).log().all()
		.when().patch(basePath);
	}
	return RestAssured.given(createRequestSpec(object, contentType, headers,tokenID))
			.when().patch(basePath);
	
}

//Delete http methods
public Response doDelete(String basePath,boolean tokenID,boolean log) {
	
	if(log) {
		
		return RestAssured.given(createRequestSpec(tokenID)).log().all()
		.when().delete(basePath);
	}
	return RestAssured.given(createRequestSpec(tokenID))
	.when().delete(basePath);
	
}
	
	

}
