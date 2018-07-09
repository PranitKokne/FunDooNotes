package com.fundoonotes.repository;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.fundoonotes.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private static final String KEY = "User";

	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, Long, User> hashOperations;

	@Autowired
	public UserRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(User user) {
		hashOperations.put(KEY, user.getId(), user);
	}

	@Override
	public void update(User user) {
		hashOperations.put(KEY, user.getId(), user);
	}

	@Override
	public void delete(Long id) {
		hashOperations.delete(KEY, id);
	}

}
