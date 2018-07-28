package com.fundoonotes.repository;

import java.util.Map;

public interface RedisRepository<T> {

	public void save(T key, T hkey, T hvalue);

	public void update(T key, T id, T object);

	public Map<T, T> findAll(T key);

	public T find(T key, T id);

	public void delete(T key, T id);
}
