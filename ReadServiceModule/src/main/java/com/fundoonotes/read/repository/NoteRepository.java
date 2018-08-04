package com.fundoonotes.read.repository;

import java.util.List;
import java.util.Map;

public interface NoteRepository {

	public List<Map<String, Object>> getNotesByUserId(String index, String type, String userId);

	public List<Map<String, Object>> getNotesBySearching(String index, String type, String userId, String queryString);

	public List<Map<Integer, String>> getLabelDetails(String index,String type,String userId);
	
}
