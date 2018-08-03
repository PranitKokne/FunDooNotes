package com.redis.cache.repo;

import java.util.Map;

import com.redis.cache.model.Customer;

public interface CustomerRepository {
	void save(Customer customer);

	Customer find(String id);

	Map<String, Customer> findAll();

	void update(Customer customer);

	void delete(String id);
}
