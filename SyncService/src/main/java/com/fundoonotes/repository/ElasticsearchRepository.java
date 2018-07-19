package com.fundoonotes.repository;

import java.util.Map;

public interface ElasticsearchRepository {

	public void insertNote(String index, String type, String id, Object note) ;

	public Map<String, Object> updateNote(String index, String type, String id, Object note);

	public void deleteNote(String index, String type, String id) ;

}
