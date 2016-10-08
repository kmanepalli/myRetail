package com.assessment.myretail;



import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.myretail.model.Price;
import com.assessment.myretail.model.Product;
import com.assessment.myretail.service.PricingService;
import com.assessment.myretail.service.ProductService;
import com.assessment.myretail.util.CannotRetrieveProductException;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
    ProductService productService;
	
	@Autowired
	PricingService pricingService;
	
    @RequestMapping("/product/{id}")
    public Product product(@PathVariable long id) throws JsonProcessingException, IOException, CannotRetrieveProductException {
    	Product product = productService.getProduct(id);
    	addPrice(id, product);
        return product;
    }
   
	private void addPrice(long id, Product product) {
		try {
			Price price = pricingService.getPrice(id);
			product.setPrice(price);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
   
}
