package com.fundoonotes.repository;

import java.util.Map;

public interface ElasticsearchRepository<T> {

	/**
	 * Purpose: This method will insert the document into the elasticsearch index.
	 *  
	 * @param index index to which the document must be indexed.
	 * @param type type of the elasticsearch index.
	 * @param id id to which the document will get indexed.
	 * @param document document containing the data in json format.
	 */
	public void insertDocument(String index, String type, String id, T document);

	/**
	 * Purpose: This method will update the elasticsearch document into the index.
	 * 
	 * @param index index to which the document must be updated.
	 * @param type type of the elasticsearch index.
	 * @param id id to which the document will get updated.
	 * @param document document containing the data in json format.
	 */
	public void updateDocument(String index, String type, String id, T document);

	/**
	 * Purpose: This method will delete the document from the elasticsearch index.
	 * 
	 * @param index index from which the document must be deleted.
	 * @param type type of the elasticsearch index.
	 * @param id id of the document to be deleted.
	 */
	public void deleteDocument(String index, String type, String id);

	/**
	 * @param index index from which the document must be retrieved.
	 * @param type type of the elasticsearch index.
	 * @param id id of the document to be retrieved.
	 * @return
	 */
	public Map<T, T> getDocumentById(String index, String type, String id);

}
