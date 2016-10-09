package com.assessment.myretail.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.assessment.myretail.model.Price;
import com.mongodb.WriteResult;

/**
 * This repository provides CRUD operations for Price objects by connecting to MongoDB.
 *
 */
@Repository
public class PriceRepository {
	@Autowired
	MongoTemplate mongoTemplate;

	/**
	 * Fetch price from MongoDB.
	 * @param productId
	 * @return Price
	 */
	public Price findByProductId(Long productId) {
		Price price = mongoTemplate.findOne(buildQuery(productId), Price.class);
		return price;
	}

	/**
	 * Update the Price in MongoDB
	 * @param productId
	 * @param price
	 * @return boolean
	 */
	public boolean updatePriceByProductId(Long productId,Price price) {
		Update update=new Update();
		update.set("value",price.getValue());
		update.set("currency",price.getCurrency());
		
		WriteResult result = mongoTemplate.updateFirst(buildQuery(productId), update, Price.class);
		return result.wasAcknowledged();
	}
	
	/**
	 * Common code to build the Query
	 * @param productId
	 * @return Query
	 */
	private Query buildQuery(Long productId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("productId").is(productId));
		return query;
	}
}
