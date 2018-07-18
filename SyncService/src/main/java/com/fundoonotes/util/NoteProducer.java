package com.fundoonotes.util;

import java.util.Map;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fundoonotes.config.RabbitMQConfig;

@Component
public class NoteProducer {

	@Autowired
	private AmqpTemplate amqpTemplate;

	public void sendNoteToProducer(Map<String, Object> message) {

		amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_NOTE, message);
	}

}
