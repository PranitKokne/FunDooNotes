package com.fundoonotes.read.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class NoteRepositoryImpl implements NoteRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(NoteRepositoryImpl.class);

	private RestHighLevelClient restHighLevelClient;

	public NoteRepositoryImpl(RestHighLevelClient restHighLevelClient) {
		this.restHighLevelClient = restHighLevelClient;
	}

	public List<Map<String, Object>> getNotesByUserId(String index, String type, String userId) {
		LOGGER.info("GET NOTES BY USER ID");
		List<Map<String, Object>> userNotes = new ArrayList<Map<String, Object>>();
		try {
			SearchRequest searchRequest = new SearchRequest(index);
			searchRequest.types(type);
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.termQuery("userId", userId));
			searchRequest.source(searchSourceBuilder);
			SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
			SearchHit[] hits = searchResponse.getHits().getHits();
			for (SearchHit note : hits) {
				userNotes.add(note.getSourceAsMap());
			}
		} catch (IOException e) {
			LOGGER.error("IOEXCEPTION WHILE READING THE NOTES BASED ON USER ID NAME", e);
		}
		return userNotes;
	}

	@Override
	public List<Map<String, Object>> getNotesBySearching(String index, String type, String userId, String queryString) {
		LOGGER.info("GET THE USER NOTES BASED ON SEARCH");
		List<Map<String, Object>> userNotes = new ArrayList<>();
		try {
			SearchRequest searchRequest = new SearchRequest(index);
			searchRequest.types(type);
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("userId", userId);
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
			boolQueryBuilder.should(QueryBuilders.queryStringQuery(queryString).field("title").field("description"))
					.should(nestedQueryForSearching("labels", queryString));
			searchSourceBuilder.query(QueryBuilders.boolQuery().must(termQueryBuilder).must(boolQueryBuilder));
			searchRequest.source(searchSourceBuilder);
			SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
			SearchHit[] hits = searchResponse.getHits().getHits();
			for (SearchHit note : hits) {
				userNotes.add(note.getSourceAsMap());
			}
		} catch (IOException e) {
			LOGGER.error("IOEXCEPTION WHILE READING THE NOTES BASED ON SEARCH ", e);
		}
		return userNotes;
	}

	@Override
	public List<Map<String, Object>> getLabelDetails(String index, String type, String userId) {
		LOGGER.info("GET THE LABEL NAME AND ID");
		List<Map<String, Object>> lableDetails = new ArrayList<>();
		try {
			SearchRequest searchRequest = new SearchRequest(index);
			searchRequest.types(type);
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.termQuery("userId", userId));
			searchRequest.source(searchSourceBuilder);
			SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
			SearchHit[] hits = searchResponse.getHits().getHits();
			for (SearchHit label : hits) {
				Map<String, Object> sourceAsMap = label.getSourceAsMap();
				Integer labelId = (Integer) sourceAsMap.get("labelId");
				String labelName = (String) sourceAsMap.get("labelName");
				Map<String, Object> labels = new HashMap<>();
				labels.put("labelId", labelId);
				labels.put("labelName", labelName);
				lableDetails.add(labels);
			}
		} catch (IOException e) {
			LOGGER.error("IOEXCEPTION WHILE READING THE LABELS BASED ON USER ID", e);
		}
		return lableDetails;
	}

	public NestedQueryBuilder nestedQueryForSearching(String path, String queryString) {
		QueryStringQueryBuilder stringQueryBuilder = QueryBuilders.queryStringQuery(queryString);
		return new NestedQueryBuilder(path, stringQueryBuilder, ScoreMode.Avg);
	}
}
