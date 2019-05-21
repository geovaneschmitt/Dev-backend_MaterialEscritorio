package com.geoschmitt.Devbackend_MaterialEscritorio.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Request")
@Component
public class Request{
	
	//COlUNAS DB
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	public static final String PRIORITY="priority";
	@Column(name = "priority", nullable = false)
	private String priority;
	
	public static final String REQUESTER="userRequester";
	@Column(name = "userRequester", nullable = false)
	private String userRequester;
	
	public static final String DESCRIPTION="description";
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "productCost", nullable = false)
	private double productCost;
	
	public static final String STATUS = "status";
	@Column(name = "status", nullable = false)
	private String status;
	
	@Column(name = "obs", nullable = true)
	private String obs;

	public Request() {
	}
	
	public Request(String priority, String userRequester, String description, double productCost, String status) {
		this.priority = priority;
		this.userRequester = userRequester;
		this.description = description;
		this.productCost = productCost;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getUserRequester() {
		return userRequester;
	}

	public void setUserRequester(String userRequester) {
		this.userRequester = userRequester;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getProductCost() {
		return productCost;
	}

	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
}
