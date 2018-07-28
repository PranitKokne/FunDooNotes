package com.fundoonotes.repository;

import java.util.Map;

public interface ElasticsearchRepository<T> {

	public void insertDocument(String index, String type, String id, T document);

	public void updateDocument(String index, String type, String id, T document);

	public void deleteDocument(String index, String type, String id);

	public Map<T, T> getDocumentById(String index, String type, String id);

}
