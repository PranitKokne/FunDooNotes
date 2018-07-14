package com.fundoonotes.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class NoteRepositoryImpl implements NoteRepository {

	private RestHighLevelClient restHighlevelClient;
	private ObjectMapper objectMapper;

	public NoteRepositoryImpl(ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient) {
		this.objectMapper = objectMapper;
		this.restHighlevelClient = restHighLevelClient;
	}

	@Override
	public void insertNote(String index, String type, String id, Object note) {
		Map dataMap = objectMapper.convertValue(note, Map.class);
		IndexRequest indexRequest = new IndexRequest(index, type, id).source(dataMap);
		try {
			restHighlevelClient.index(indexRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> updateNote(String index, String type, String id, Object note) {
		UpdateRequest updateRequest = new UpdateRequest(index, type, id).fetchSource(true);
		Map<String, Object> error = new HashMap<>();
		error.put("error", "error in updating note");
		try {
			String noteJson = objectMapper.writeValueAsString(note);
			updateRequest.doc(noteJson, XContentType.JSON);
			UpdateResponse updateResponse = restHighlevelClient.update(updateRequest);
			Map<String, Object> sourceAsMap = updateResponse.getGetResult().sourceAsMap();
			return sourceAsMap;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return error;
	}

	@Override
	public void deleteNote(String index, String type, String id) {
		DeleteRequest deleteRequest = new DeleteRequest(index, type, id);
		try {
			restHighlevelClient.delete(deleteRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
