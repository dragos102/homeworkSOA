package com.globalsoftwaresupport.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity

public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 30)
	@JsonProperty("model")
	private String model;

	@Column(length = 30)
	@JsonProperty("year")
	private String year;

	@Column(length = 30)
	@JsonProperty("price")
	private Integer price;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;


	public Vehicle() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
