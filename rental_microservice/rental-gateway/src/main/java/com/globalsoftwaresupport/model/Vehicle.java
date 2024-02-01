package com.globalsoftwaresupport.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vehicle {


	@JsonProperty("model")
	private String model;


	@JsonProperty("year")
	private String year;

	@JsonProperty("price")
	private Integer price;

	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


}
