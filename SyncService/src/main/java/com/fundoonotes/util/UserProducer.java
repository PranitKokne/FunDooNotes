package com.fundoonotes.util;

import org.jboss.logging.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fundoonotes.config.RabbitMQConfig;

@Component
public class UserProducer {

	private static final Logger logger = Logger.getLogger(UserProducer.class);

	@Autowired
	private AmqpTemplate amqpTemplate;

	public <T> void sendToProducer(T obj) {
		logger.info("sending the object to the topic exchange");
		amqpTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, RabbitMQConfig.routingKey, obj);
		logger.info("object sent to the topic exchange successfully");
	}

}
