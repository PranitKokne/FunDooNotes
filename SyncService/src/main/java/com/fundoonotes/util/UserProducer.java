package com.fundoonotes.util;

import java.util.HashMap;
import java.util.Map;

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
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("object", obj);
		message.put("type", "redis");
		message.put("operation", "insert");
		amqpTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, RabbitMQConfig.routingKey, message);
		logger.info("object sent to the topic exchange successfully");
	}

}
