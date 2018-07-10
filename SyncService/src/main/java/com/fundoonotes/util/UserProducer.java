package com.fundoonotes.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fundoonotes.config.RabbitMQConfig;
import com.fundoonotes.model.User;

@Component
public class UserProducer {

	@Autowired
	private AmqpTemplate amqpTemplate;

	public void sendToUserProducer(User user) {
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("type", "redis");
		message.put("operation", "insert");
		message.put("object", user);
		message.put("HK", user.getId());
		message.put("KEY", user.getKey());
		amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_USER, message);
	}

}
