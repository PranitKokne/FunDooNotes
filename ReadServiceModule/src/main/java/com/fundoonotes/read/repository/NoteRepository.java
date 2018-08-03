package com.fundoonotes.read.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface NoteRepository {

	public List<Map<String, Object>> getNotesByUserId(String index, String type, String userId);

	public List<Map<String, Object>> getNotesByState(String index, String type, String field, String userId);

	public List<Map<String, Object>> getNotesByLabelName(String index, String type, String userId, String labelValue);

	public List<Map<String, Object>> getNotesBySearching(String index, String type, String userId, String queryString);

	public Set<String> getAllLabelNames(String index, String type, String userId);

	public List<Map<String, Object>> getReminderNotesOfUser(String index, String type, String userId);

	public Set<String> getAllNewLabelNames(String index, String type, String userId);

}
