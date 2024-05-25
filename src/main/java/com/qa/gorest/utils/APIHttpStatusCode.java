package com.qa.gorest.utils;

public enum APIHttpStatusCode {
	
	OK_200(200,"OK"),
	CREATED_201(201,"CREATED"),
	NO_CONTENT_204(204,"NO_CONTENT"),
	BAD_REQUEST_400(400,"BAD REQUEST"),
	UNAUTHORIZED_401(401,"UNAUTHORIZED"),
	FORBIDDEN_403(403,"FORBIDDEN"),
	NOT_FOUND_404(404,"NOT_FOUND"),
	INTERNAL_SERVER_ERROR_500(500,"INTERNAL_SERVER_ERROR");
	
	
	
	
	private final int code;
	private final String message;
	
	private APIHttpStatusCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}

}
