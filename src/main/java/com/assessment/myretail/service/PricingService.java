package com.assessment.myretail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.myretail.model.Price;
import com.assessment.myretail.repository.PriceRepository;

@Service
public class PricingService {
	private static final Logger log = LoggerFactory
			.getLogger(ProductService.class);

	@Autowired
	PriceRepository priceRepo;
	
	/**
	 * Fetch Price data from the Pricing Repository.
	 * @param productId
	 * @return Price
	 */
	public Price getPrice(Long productId) {
		Price price = null;
		try {
			
			price = priceRepo.findByProductId(productId);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return price;
	}
	
	
	public boolean updatePrice(Long productId,Price price) {
		boolean acknowledged=false;
		try {
			
			acknowledged = priceRepo.updatePriceByProductId(productId,price);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return acknowledged;
	}
}
