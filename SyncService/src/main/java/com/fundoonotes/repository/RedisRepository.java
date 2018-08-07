package com.fundoonotes.repository;

import java.util.Map;

/**
 * Purpose: The interface acts as a redis repository.
 * 
 * @author Pranit_Kokne
 * @version 1.0
 * @since 06-08-2018
 */
public interface RedisRepository<T> {

	/**
	 * Purpose: Set the value of a hash hashKey.
	 * 
	 * @param key
	 *            must not be null.
	 * @param hkey
	 *            must not be null.
	 * @param hvalue
	 *            must not be null.
	 */
	public void save(T key, T hkey, T hvalue);

	/**
	 * Purpose: Set the value of a hash hashKey.
	 * 
	 * @param key
	 *            must not be null.
	 * @param hkey
	 *            must not be null.
	 * @param hvalue
	 *            must not be null.
	 */
	public void update(T key, T hkey, T hvalue);

	/**
	 * Purpose: Delete given hash hashKeys.
	 * 
	 * @param key
	 *            must not be null.
	 * @param hkey
	 *            must not be null.
	 */
	public void delete(T key, T hkey);

	/**
	 * Purpose: Get value for given hashKey from hash at key.
	 * 
	 * @param key
	 *            must not be null.
	 * @param hkey
	 *            must not be null.
	 * @return null when used in pipeline / transaction.
	 */
	public T find(T key, T hkey);

	/**
	 * Purpose: Get entire hash stored at key.
	 * 
	 * @param key
	 *            must not be null.
	 * @return null when used in pipeline / transaction.
	 */
	public Map<T, T> findAll(T key);
}
