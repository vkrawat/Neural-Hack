package com.chargemanag1.bankmanag1.Entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RulesId implements Serializable{
	
	
	private String category;
	
	private String operation_type;

	public RulesId(String category, String operation_type) {
		super();
		this.category = category;
		this.operation_type = operation_type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOperation_type() {
		return operation_type;
	}

	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}
	
	
}
