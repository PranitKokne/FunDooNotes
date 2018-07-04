package com.fundoonotes.util;

import org.jboss.logging.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fundoonotes.user.config.RabbitMQConfig;
import com.fundoonotes.user.model.Mail;

@Component
public class EmailProducer {

	private static final Logger logger = Logger.getLogger(EmailProducer.class);

	@Autowired
	private AmqpTemplate amqpTemplate;

	public void produceEmail(Mail mail) {
		logger.info("email is ready");
		amqpTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, RabbitMQConfig.routingKey, mail);
		logger.info("transfer to the topic exchange");
	}
}
