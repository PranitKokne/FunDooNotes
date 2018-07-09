package com.fundoonotes.util;

import org.jboss.logging.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fundoonotes.config.RabbitMQConfig;
import com.fundoonotes.model.User;

@Component
public class UserProducer {

	private static final Logger logger = Logger.getLogger(UserProducer.class);

	@Autowired
	private AmqpTemplate amqpTemplate;

	public void saveOrUpdate(User user) {
		amqpTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, RabbitMQConfig.routingKeyOne, user);
	}

	public void delete(Long id) {
		amqpTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, RabbitMQConfig.routingKeyThree, id);
	}
}
