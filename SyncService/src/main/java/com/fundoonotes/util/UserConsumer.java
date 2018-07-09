package com.fundoonotes.util;

import org.jboss.logging.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.fundoonotes.config.RabbitMQConfig;

@Component
public class UserConsumer {

	private static final Logger logger = Logger.getLogger(UserConsumer.class);

	@RabbitListener(queues = RabbitMQConfig.queueName, containerFactory = "containerFactory")
	public void receiveModel(Message<?> message) {
		logger.info("Object consumed by the subscriber");
		logger.info(message.getPayload());
		logger.info("in the user consumer");
	}
}
