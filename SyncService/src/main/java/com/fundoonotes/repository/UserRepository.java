package com.fundoonotes.repository;

import java.util.Map;

public interface UserRepository {

	public void save(String key, String id, Object user);

	public Map<String, Object> findAll(String key);

	public Object find(String key, String id);

	public void delete(String key, String id);
}
