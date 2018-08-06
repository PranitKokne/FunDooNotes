package com.fundoonotes.repository;

import java.io.IOException;
import java.util.Map;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ElasticsearchRepositoryImpl<T> implements ElasticsearchRepository<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchRepositoryImpl.class);

	private RestHighLevelClient restHighlevelClient;
	private ObjectMapper objectMapper;

	public ElasticsearchRepositoryImpl(ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient) {
		this.objectMapper = objectMapper;
		this.restHighlevelClient = restHighLevelClient;
	}

	@Override
	public void insertDocument(String index, String type, String id, T document) {
		Map<?, ?> dataMap = objectMapper.convertValue(document, Map.class);
		IndexRequest indexRequest = new IndexRequest(index, type, id).source(dataMap);
		try {
			restHighlevelClient.index(indexRequest);
			LOGGER.info("DOCUMENT ADDEDED SUCCESSFULLY TO THE INDEX");
		} catch (IOException e) {
			LOGGER.error("IOException WHILE INDEXING THE DOCUMENT", e);
		}
	}

	@Override
	public void updateDocument(String index, String type, String id, T document) {
		Map<T, T> record = getDocumentById(index, type, id);
		if (record != null) {
			UpdateRequest updateRequest = new UpdateRequest(index, type, id).fetchSource(true);
			try {
				String noteJson = objectMapper.writeValueAsString(document);
				updateRequest.doc(noteJson, XContentType.JSON);
				restHighlevelClient.update(updateRequest);
				LOGGER.info("DOCUMENT UPDATED SUCCESSFULLY TO THE INDEX");
				return;
			} catch (JsonProcessingException e) {
				LOGGER.error("JSON PROCESSING EXCEPTION WHILE UPDATING DOCUMENT", e);
			} catch (IOException e) {
				LOGGER.error("IOEXCEPTION WHILE UPDATING THE DOCUMENT", e);
			}
		}
		LOGGER.error("ERROR IN UPDATING THE UNAVAILABLE DOCUMENT");
	}

	@Override
	public void deleteDocument(String index, String type, String id) {
		Map<T, T> record = getDocumentById(index, type, id);
		if (record != null) {
			DeleteRequest deleteRequest = new DeleteRequest(index, type, id);
			try {
				restHighlevelClient.delete(deleteRequest);
			} catch (IOException e) {
				LOGGER.error("IOEXCEPTION WHILE DELETING THE DOCUMENT", e);
			}
			LOGGER.info("DOCUMENT DELETED SUCCESSFULLY FROM THE INDEX");
			return;
		}
		LOGGER.error("ERROR IN DELETING THE UNAVAILABLE DOCUMENT");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<T, T> getDocumentById(String index, String type, String id) {
		Map<T, T> document = null;
		try {
			GetRequest getRequest = new GetRequest(index, type, id);
			GetResponse getResponse = restHighlevelClient.get(getRequest);
			document = (Map<T, T>) getResponse.getSourceAsMap();
		} catch (IOException e) {
			LOGGER.error("IOEXCEPTION WHILE READING THE DOCUMENT", e);
		}
		return document;
	}

}
