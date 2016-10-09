package com.assessment.myretail.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Price {

	public String currency;
	public double value;
	public Long productId;
	Price(Long productId, double value, String currency) {
		this.productId = productId;
		this.value = value;
		this.currency = currency;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	@JsonIgnore
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
