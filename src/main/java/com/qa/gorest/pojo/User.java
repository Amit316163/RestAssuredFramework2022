package com.qa.gorest.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
		@JsonProperty("id")
		private Integer id;
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("gender")
		private String gender;
		
		@JsonProperty("email")
		private String email;
		
		@JsonProperty("status")
		private String status;
		
		public User(String name, String gender, String email, String status) {
			this.name = name;
			this.gender = gender;
			this.email = email;
			this.status = status;
		}
	
}
