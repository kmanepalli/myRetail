package com.assessment.myretail.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.assessment.myretail.model.Product;
import com.assessment.myretail.util.CannotRetrieveProductException;

public class ProductSerivceTest {

	
	@Test
	public void testParseResponse() {
		ProductService productService = new ProductService();
		String response = "{\"product_composite_response\":{\"items\":[{\"identifier\":[{\"id_type\":\"DPCI\",\"id\":\"057-10-3003\","
			+"\"is_primary\":null,\"source\":\"Online and Store\"},{\"id_type\":\"TCIN\",\"id\":\"15117729\",\"is_primary\":null,"
				+"\"source\":\"Online\"}],\"general_description\":\"Apple iPad Air 2 Wi-Fi 16GB, Gold\"}]}}";
		
		try {
			Product product=productService.parseReponse(response);
			assertEquals( "Apple iPad Air 2 Wi-Fi 16GB, Gold", product.getName());
			assertEquals(15117729,product.getId());
		} catch (Exception e){
			
		}
		
	}
	
	@Test
	public void testParseResponseWithError() {
		ProductService productService = new ProductService();
		String response = "{\"product_composite_response\":{\"items\":[{\"identifier\":[{\"id_type\":\"TCIN\",\"id\":\"151177294\","
				+ "\"is_primary\":null,\"source\":null}],\"errors\":"
				+ "[{\"message\":\"Not valid product in system: This product ID does not represent a valid product\"}]}]}}";
		try {
			productService.parseReponse(response);
			
		} catch (Exception e) {
			assertEquals(CannotRetrieveProductException.class,e.getClass());
		}
		
	}

}
