package com.bridgelabz.elastic.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Repository;

import com.bridgelabz.elastic.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class BookDao {

	private final String INDEX = "bookdata";
	private final String TYPE = "books";
	private RestHighLevelClient restHighLevelClient;
	private ObjectMapper objectMapper;

	public BookDao(ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient) {
		this.objectMapper = objectMapper;
		this.restHighLevelClient = restHighLevelClient;
	}

	public Map<String, Object> getBookById(String id) {
		GetRequest getRequest = new GetRequest(INDEX, TYPE, id);
		GetResponse getResponse = null;

		try {
			getResponse = restHighLevelClient.get(getRequest);
		} catch (IOException e) {
			e.getLocalizedMessage();
		}
		Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
		return sourceAsMap;
	}

	public void deleteBookById(String id) {
		DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, id);

		try {
			DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest);
		} catch (IOException e) {
			e.getLocalizedMessage();
		}
	}

	public Map<String, Object> updateBookById(Book book, String id) {
		UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, id).fetchSource(true);
		Map<String, Object> error = new HashMap<>();
		error.put("error", "unable to update book");
		try {
			String bookJson = objectMapper.writeValueAsString(book);
			updateRequest.doc(bookJson, XContentType.JSON);
			UpdateResponse updateResponse = restHighLevelClient.update(updateRequest);
			Map<String, Object> sourceAsMap = updateResponse.getGetResult().sourceAsMap();
			return sourceAsMap;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return error;
	}

	public Book insertBook(Book book) {
		Map dataMap = objectMapper.convertValue(book, Map.class);
		IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, book.getId());
		indexRequest.source(dataMap);

		try {
			IndexResponse indexResponse = restHighLevelClient.index(indexRequest);
			System.out.println(indexResponse.toString());
		} catch (IOException e) {
			e.getLocalizedMessage();
		}
		
		return book;
	}

}
