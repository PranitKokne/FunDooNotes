package com.fundoonotes.util;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import com.fundoonotes.config.RabbitMQConfig;
import com.fundoonotes.service.SyncService;

@Component
public class EventListener {

	@Autowired
	private SyncService syncService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EventListener.class);

	@RabbitListener(queues = RabbitMQConfig.REDIS_QUEUE_NAME)
	public void listenToUser(Message<Map<String, Object>> message) {
		LOGGER.info("RECEIEVED THE REDIS DATA FROM THE QUEUE");
		LOGGER.info("REDIS : " + message.getPayload());
		LOGGER.info("REDIS : " + message.getHeaders());
		syncService.sendRedisDataToSyncService(message.getPayload());
	}

	/*@RabbitListener(queues = RabbitMQConfig.ELASTIC_QUEUE_NAME)
	public void listenToNote(Message<Map<String, Object>> message) {
		LOGGER.info("RECEIEVED THE ELASTIC SEARCH DATA FROM THE QUEUE");
		LOGGER.info("ES : " + message.getPayload());
		LOGGER.info("ES : " + message.getHeaders());
		syncService.sendElasticDataToSyncService(message.getPayload());
	}*/

}
