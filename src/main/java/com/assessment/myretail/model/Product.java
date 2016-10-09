package com.assessment.myretail.model;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Product Model
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	private long product_id;
	private String general_description;
	@Autowired
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Price price;

	public Product() {

	}

	public long getId() {
		return product_id;
	}

	public void setId(long id) {
		this.product_id = id;
	}

	public String getName() {
		return general_description;
	}

	public void setName(String name) {
		this.general_description = name;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

}
