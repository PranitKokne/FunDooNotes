package com.fundoonotes.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundoonotes.repository.ElasticsearchRepository;
import com.fundoonotes.repository.RedisRepository;
import com.fundoonotes.util.ElasticSearchMapping;

@Service
public class SyncService<T> {

	@Autowired
	RedisRepository<T> redisRepository;

	@Autowired
	ElasticsearchRepository<T> elasticSearchRepository;

	@Autowired
	ElasticSearchMapping<T> elasticSearchMapping;

	private static final Logger LOGGER = LoggerFactory.getLogger(SyncService.class);

	public void sendRedisDataToSyncService(Map<T, T> message) {
		LOGGER.info("****PROCESSING THE REDIS DATA****");
		LOGGER.info("REDIS DATA FROM SYNC SERVICE" + message.toString());
		T key = message.get("KEY");
		T hkey = message.get("HK");
		T hvalue = message.get("object");
		T task = message.get("operation");
		String operation = task.toString();
		if (operation != null) {
			if (operation.equalsIgnoreCase("insert")) {
				redisRepository.save(key, hkey, hvalue);
			} else if (operation.equalsIgnoreCase("update")) {
				redisRepository.update(key, hkey, hvalue);
			} else if (operation.equalsIgnoreCase("delete")) {
				redisRepository.delete(key, hkey);
			}
			return;
		}
		LOGGER.error("NULL POINTER EXCEPTION WHILE RECEIVING THE DATA FROM QUEUE", NullPointerException.class);
	}

	public void sendElasticDataToSyncService(Map<T, T> message) {
		LOGGER.info("****PROCESSING THE ELASTIC SEARCH DATA****");
		LOGGER.info("ELASTIC DATA FROM SYNC SERVICE" + message.toString());
		String index = (String) message.get("index");
		String type = (String) message.get("type");
		String id = (String) message.get("id");
		T document = message.get("document");
		T task = message.get("operation");
		String operation = task.toString();
		if (operation != null) {
			if (!elasticSearchMapping.isIndexPresent(index)) {
				ElasticSearchMapping.createIndexMapping(index);
			}
			if (operation.equalsIgnoreCase("index")) {
				elasticSearchRepository.insertDocument(index, type, id, document);
			} else if (operation.equalsIgnoreCase("update")) {
				elasticSearchRepository.updateDocument(index, type, id, document);
			} else if (operation.equalsIgnoreCase("delete")) {
				elasticSearchRepository.deleteDocument(index, type, id);
			}
			return;
		}
		LOGGER.error("NULL POINTER EXCEPTION WHILE RECEIVING THE DATA FROM QUEUE", NullPointerException.class);
	}

}
