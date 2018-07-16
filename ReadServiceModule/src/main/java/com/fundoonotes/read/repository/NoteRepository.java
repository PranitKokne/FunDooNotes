package com.fundoonotes.read.repository;

import java.util.Map;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Repository;

@Repository
public class NoteRepository {

	private RestHighLevelClient restHighLevelClient;

	public NoteRepository(RestHighLevelClient restHighLevelClient) {
		this.restHighLevelClient = restHighLevelClient;
	}

	public Map<String, Object> getNoteById(String index, String type, String id) {
		GetRequest getRequest = new GetRequest(index, type, id);
		GetResponse getResponse = null;
		try {
			getResponse = restHighLevelClient.get(getRequest);
		} catch (java.io.IOException e) {
			e.getLocalizedMessage();
		}
		Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
		return sourceAsMap;
	}
}
