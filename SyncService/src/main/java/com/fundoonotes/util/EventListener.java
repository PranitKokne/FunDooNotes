package com.fundoonotes.util;

import java.util.Map;
import org.jboss.logging.Logger;
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

	private static final Logger LOGGER = Logger.getLogger(EventListener.class);

	@RabbitListener(queues = RabbitMQConfig.USER_QUEUE, containerFactory = "containerFactory")
	public void listenToUser(Message<Map<String, Object>> message) {
		LOGGER.info(message.getPayload());
		LOGGER.info(message.getHeaders());
		syncService.sendUserToSyncService(message.getPayload());
	}

	@RabbitListener(queues = RabbitMQConfig.NOTE_QUEUE, containerFactory = "containerFactory")
	public void listenToNote(Message<Map<String, Object>> message) {
		LOGGER.info(message.getPayload());
		LOGGER.info(message.getHeaders());
		syncService.sendNoteToSyncService(message.getPayload());
	}

}
