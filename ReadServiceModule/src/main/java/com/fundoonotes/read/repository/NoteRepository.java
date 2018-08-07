package com.fundoonotes.read.repository;

import java.util.List;
import java.util.Map;

/**
 * Purpose: The interface acts as note repository.
 * 
 * @author Pranit_Kokne
 * @version 1.0
 * @since 07-08-2018
 */
public interface NoteRepository {

	/**
	 * Purpose: The method retrieves the notes of a user from elasticsearch index
	 * based on the user id.
	 * 
	 * @param index
	 *            name of the elasticsearch index
	 * @param type
	 *            name of the elasticsearch type
	 * @param userId
	 *            userId is used to uniquely identify which user's notes should be
	 *            retrieved
	 * @return list of notes related to a user
	 */
	public List<Map<String, Object>> getNotesByUserId(String index, String type, String userId);

	/**
	 * Purpose: The method retrieves the notes of a user which contains the
	 * specified keywords.
	 * 
	 * @param index
	 *            name of the elasticsearch index
	 * @param type
	 *            name of the elasticsearch type
	 * @param userId
	 *            userId is used to uniquely identify which user's notes should be
	 *            retrieved
	 * @param queryString
	 *            search keywords
	 * @return list of notes based on the search keywords
	 */
	public List<Map<String, Object>> getNotesBySearching(String index, String type, String userId, String queryString);

	/**
	 * @param index
	 *            name of the elasticsearch index
	 * @param type
	 *            name of the elasticsearch type
	 * @param userId
	 *            userId is used to uniquely identify which user's labels details
	 *            should be retrieved
	 * @return label id's and name's of the specified user
	 */
	public List<Map<String, Object>> getLabelDetails(String index, String type, String userId);

}
