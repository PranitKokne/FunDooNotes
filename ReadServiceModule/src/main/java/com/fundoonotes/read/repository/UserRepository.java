package com.fundoonotes.read.repository;

/**
 * Purpose: The interface acts as user repository.
 * 
 * @author Pranit_Kokne
 * @version 1.0
 * @since 07-08-2018
 */
public interface UserRepository {

	/**
	 * Purpose: The method is used to retrieve the user details from redis database
	 * based on key and hashkey.
	 * 
	 * @param key
	 *            must not be null.
	 * @param hashKey
	 *            must not be null.
	 * @return the user details from redis database
	 */
	public Object getUserById(String key, String hashKey);
}
