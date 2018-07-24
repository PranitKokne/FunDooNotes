package com.fundoonotes.read.repository;

import java.util.List;
import java.util.Map;

public interface NoteRepository {

	public List<Map<String, Object>> getNotesByUserId(String index, String type, String fieldName, String value);

	public List<Map<String, Object>> getNotesByState(String index, String type, String field, String userId);

	public List<Map<String, Object>> getNotesByLabelName(String index, String type, String userId, String labelValue);
}
