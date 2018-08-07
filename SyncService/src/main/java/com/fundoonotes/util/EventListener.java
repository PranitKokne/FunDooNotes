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

/******************************************************************************
 * 
 *
 * Purpose:This class plays a role of listener in a message queuing system. The
 * listener is listening to two different queues at a time and storing the
 * message into redis and elasticsearch.
 *
 * @author Pranit_Kokne
 * @version 1.0
 * @since 06-08-2018
 *
 ******************************************************************************/
@Component
public class EventListener<T> {

	@Autowired
	private SyncService<T> syncService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EventListener.class);

	/**
	 * Purpose: The method is receiving the user data from the queue and sending it
	 * to sync service for syncing it in redis.
	 * 
	 * @param message
	 *            message received from the queue.
	 */
	@RabbitListener(queues = RabbitMQConfig.REDIS_QUEUE_NAME)
	public void listenToUser(Message<Map<T, T>> message) {
		LOGGER.info("RECEIEVED THE REDIS DATA FROM THE QUEUE");
		LOGGER.info("REDIS : " + message.getPayload());
		LOGGER.info("REDIS : " + message.getHeaders());
		syncService.sendRedisDataToSyncService(message.getPayload());
	}

	/**
	 * Purpose: The method is receiving the note data from the queue and sending it
	 * to sync service for syncing it in elasticsearch.
	 * 
	 * @param message
	 *            message received from the queue.
	 */
	@RabbitListener(queues = RabbitMQConfig.ELASTIC_QUEUE_NAME)
	public void listenToNote(Message<Map<T, T>> message) {
		LOGGER.info("RECEIEVED THE ELASTIC SEARCH DATA FROM THE QUEUE");
		LOGGER.info("ES : " + message.getPayload());
		LOGGER.info("ES : " + message.getHeaders());
		syncService.sendElasticDataToSyncService(message.getPayload());
	}

}
