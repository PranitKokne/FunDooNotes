package com.fundoonotes.repository;

import javax.annotation.PostConstruct;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private static final Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class);

	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String,Long, Object> hashOperations;

	@Autowired
	public UserRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(String key, Long hkey, Object hvalue) {
		hashOperations.put(key, hkey, hvalue);
		LOGGER.info("ADDED INTO THE REDIS");
	}

}
