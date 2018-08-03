package com.redis.cache.repo;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.redis.cache.model.Customer;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	private static final String KEY = "Employee";
	 
	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, String, Customer> hashOperations;
 
	@Autowired
	public CustomerRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
 
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}
 
	@Override
	public void save(Customer customer) {
		hashOperations.put(KEY, customer.getId(), customer);
	}
 
	@Override
	public Customer find(String id) {
		return hashOperations.get(KEY, id);
	}
 
	@Override
	public Map<String, Customer> findAll() {
		return hashOperations.entries(KEY);
	}
 
	@Override
	public void update(Customer customer) {
		hashOperations.put(KEY, customer.getId(), customer);
	}
 
	@Override
	public void delete(String id) {
		hashOperations.delete(KEY, id);
	}
}
