package com.globalsoftwaresupport.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class User {

	@JsonProperty("first_name")
	@NotEmpty(message = "The first name can not be null or empty")
	private String firstName;


	@JsonProperty("last_name")
	@NotEmpty(message = "The last name can not be null or empty")
	private String lastName;


	@JsonProperty("email")
	@NotEmpty(message = "The email can not be null or empty")
	private String email;


	@JsonProperty("password")
	@NotEmpty(message = "The password can not be null or empty")
	private String password;


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
