package com.fundoonotes.repository;

import java.util.Map;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepositoryImpl<T> implements RedisRepository<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisRepositoryImpl.class);

	private RedisTemplate<T, T> redisTemplate;
	private HashOperations<T, T, T> hashOperations;

	@Autowired
	public RedisRepositoryImpl(RedisTemplate<T, T> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(T key, T id, T object) {
		hashOperations.put(key, id, object);
		LOGGER.info("ADDED/UPDATED THE DATA INTO THE REDIS");
	}

	@Override
	public void update(T key, T id, T object) {
		T retrievedObject = find(key, id);
		if (retrievedObject != null) {
			hashOperations.put(key, id, object);
			LOGGER.info("UPDATED THE DATA INTO THE REDIS");
			return;
		}
		LOGGER.error("ERROR IN UPDATING THE UNAVAILABLE DATA INTO THE REDIS");
	}

	@Override
	public Map<T, T> findAll(T key) {
		return hashOperations.entries(key);
	}

	@Override
	public T find(T key, T id) {
		return hashOperations.get(key, id);
	}

	@Override
	public void delete(T key, T id) {
		T retrievedObject = find(key, id);
		if (retrievedObject != null) {
			hashOperations.delete(key, id);
			LOGGER.info("DELETED THE DATA SUCCESSFULLY FROM THE REDIS");
			return;
		}
		LOGGER.error("ERROR IN DELETING THE UNAVAILABLE DATA FROM THE REDIS");
	}

}
