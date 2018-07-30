package com.fundoonotes.read.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
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

	public List<Map<String, Object>> getNotesByState(String index, String type, String field, String userId) {
		LOGGER.info("GET TRASH/ARCHIVE NOTES BY USER ID");
		List<Map<String, Object>> userNotes = new ArrayList<Map<String, Object>>();
		try {
			SearchRequest searchRequest = new SearchRequest(index);
			searchRequest.types(type);
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("userId", userId))
					.must(QueryBuilders.termQuery(field, true)));
			searchRequest.source(searchSourceBuilder);
			SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
			SearchHit[] hits = searchResponse.getHits().getHits();
			for (SearchHit note : hits) {
				userNotes.add(note.getSourceAsMap());
			}
		} catch (IOException e) {
			LOGGER.error("IOEXCEPTION WHILE READING THE NOTES BASED ON STATE", e);
		}
		return userNotes;
	}

	@Override
	public List<Map<String, Object>> getNotesByLabelName(String index, String type, String userId, String labelName) {
		LOGGER.info("GET THE USER NOTES BY LABEL NAME");
		List<Map<String, Object>> userNotes = new ArrayList<>();
		try {
			SearchRequest searchRequest = new SearchRequest(index);
			searchRequest.types(type);
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("userId", userId))
					.must(nestedBoolQuery(labelName)));
			searchRequest.source(searchSourceBuilder);
			SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
			SearchHit[] hits = searchResponse.getHits().getHits();
			for (SearchHit note : hits) {
				userNotes.add(note.getSourceAsMap());
			}
		} catch (IOException e) {
			LOGGER.error("IOEXCEPTION WHILE READING THE NOTES BASED ON LABEL NAME", e);
		}
		return userNotes;
	}

	public NestedQueryBuilder nestedBoolQuery(String labelName) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("label.labelName", labelName);
		boolQueryBuilder.must(matchQueryBuilder);
		return QueryBuilders.nestedQuery("label", boolQueryBuilder, ScoreMode.Avg);
	}

	@Override
	public List<Map<String, Object>> getNotesBySearching(String index, String type, String userId, String queryString) {
		LOGGER.info("GET THE USER NOTES BASED ON SEARCH");
		List<Map<String, Object>> userNotes = new ArrayList<>();
		try {
			SearchRequest searchRequest = new SearchRequest(index);
			searchRequest.types(type);
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("userId", userId))
					.must(QueryBuilders.queryStringQuery(queryString)));
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
	@SuppressWarnings("unchecked")
	public Set<String> getAllLabelNames(String index, String type, String userId) {
		LOGGER.info("GET THE LABEL NAMES ");
		Set<String> labelNames = new LinkedHashSet<>();
		try {
			SearchRequest searchRequest = new SearchRequest(index);
			searchRequest.types(type);
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.termQuery("userId", userId));
			searchRequest.source(searchSourceBuilder);
			SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
			SearchHit[] hits = searchResponse.getHits().getHits();
			for (SearchHit note : hits) {
				Map<String, Object> sourceAsMap = note.getSourceAsMap();
				Map<String, Object> label = (Map<String, Object>) sourceAsMap.get("label");
				List<String> names = (List<String>) label.get("labelName");
				for (String name : names) {
					labelNames.add(name);
				}
			}
		} catch (IOException e) {
			LOGGER.error("IOEXCEPTION WHILE READING THE LABEL NAMES ", e);
		}
		return labelNames;
	}
}
