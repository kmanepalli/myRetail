package com.assessment.myretail.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assessment.myretail.model.Product;
import com.assessment.myretail.util.CannotRetrieveProductException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductService {

	/**
	 * Fetch product from API
	 * @param productId
	 * @return Product
	 * @throws JsonProcessingException
	 * @throws IOException
	 * @throws CannotRetrieveProductException
	 */
	public Product getProduct(Long productId) throws JsonProcessingException,
			IOException, CannotRetrieveProductException {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(
				"https://api.target.com/products/v3/" + productId
						+ "?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz",
				String.class);

		Product product = parseReponse(response.getBody());

		return product;
	}
	
	/**
	 * Parse json response from the API.
	 * @param responseBody
	 * @return Product
	 * @throws IOException
	 * @throws JsonProcessingException
	 * @throws CannotRetrieveProductException
	 */
	Product parseReponse(String responseBody) throws IOException,
			JsonProcessingException, CannotRetrieveProductException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(responseBody);
		JsonNode name = root.path("product_composite_response");
		JsonNode items = name.path("items").get(0);
		if (items.path("errors") != null && items.path("errors").size() >= 1) {

			throw new CannotRetrieveProductException(
					"Unable to retrieve Product. Please check the Product ID");
		}

		Product product = new Product();
		product.setId(items.path("identifier").get(1).path("id").asLong());
		product.setName(items.path("general_description").asText());
		return product;
	}
}
