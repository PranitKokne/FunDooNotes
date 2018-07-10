package com.fundoonotes.util;

import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.fundoonotes.config.RabbitMQConfig;

@Component
public class EventListener {

	private static final Logger LOGGER = Logger.getLogger(EventListener.class);

	@RabbitListener(queues = RabbitMQConfig.USER_QUEUE, containerFactory = "containerFactory")
	public void listenToUser(Message<Map<String, Object>> message) {
		LOGGER.info(message.getPayload());
		LOGGER.info(message.getHeaders());
	}

	@RabbitListener(queues = RabbitMQConfig.NOTE_QUEUE, containerFactory = "containerFactory")
	public void listenToNote(Message message) {
		LOGGER.info(message.getPayload());
		LOGGER.info(message.getHeaders());
	}

}
