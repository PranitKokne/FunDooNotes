package com.fundoonotes.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElasticSearchMapping {

	private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchMapping.class);

	@Autowired
	private RestHighLevelClient restHighLevelClient;

	public boolean isIndexPresent(String indexName) {
		GetIndexRequest indexRequest = new GetIndexRequest();
		indexRequest.indices(indexName);
		boolean exists = false;
		try {
			exists = restHighLevelClient.indices().exists(indexRequest);
		} catch (IOException e) {
			LOGGER.error("IOEXCEPTION WHILE CHECKING INDEX IS PRESENT OR NOT", e);
		}
		return exists;
	}

	public static void createIndexMapping(String indexName) {
		CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
		Map<String, Object> jsonMap = new HashMap<>();
		Map<String, Object> note = new HashMap<>();
		Map<String, Object> properties = new HashMap<>();

		Map<String, Object> title = new HashMap<>();
		title.put("type", "text");
		Map<String, Object> description = new HashMap<>();
		description.put("type", "text");
		Map<String, Object> color = new HashMap<>();
		color.put("type", "keyword");
		Map<String, Object> createdDate = new HashMap<>();
		createdDate.put("type", "date");
		Map<String, Object> updatedDate = new HashMap<>();
		updatedDate.put("type", "date");
		Map<String, Object> userId = new HashMap<>();
		userId.put("type", "text");
		Map<String, Object> noteId = new HashMap<>();
		noteId.put("type", "text");

		properties.put("userId", userId);
		properties.put("id", noteId);
		properties.put("title", title);
		properties.put("description", description);
		properties.put("createdDate", createdDate);
		properties.put("updatedDate", updatedDate);
		properties.put("color", color);

		note.put("properties", properties);

		jsonMap.put("note", note);

		createIndexRequest.mapping("note", jsonMap);

		LOGGER.info("INDEX CREATED SUCCESSFULLY");
	}

}
