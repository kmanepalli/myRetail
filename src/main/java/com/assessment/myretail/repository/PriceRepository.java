package com.assessment.myretail.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.assessment.myretail.model.Price;

@Repository
public class PriceRepository {
	@Autowired
	MongoTemplate mongoTemplate;
	
	public Price findByProductId(Long productId) {
		Query query2 = new Query();
		query2.addCriteria(Criteria.where("productId").is(productId));
		Price price = mongoTemplate.findOne(query2, Price.class);
		return price;
	}
	
}
