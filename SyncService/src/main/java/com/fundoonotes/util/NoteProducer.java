package com.fundoonotes.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fundoonotes.config.RabbitMQConfig;
import com.fundoonotes.model.Note;

@Component
public class NoteProducer {

	@Autowired
	private AmqpTemplate amqpTemplate;

	public void sendToNoteProducer(Note note) {
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("type", "elastic");
		message.put("operation", "insert");
		message.put("Note", note);
		message.put("id", note.getId());
		amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_NOTE, message);
	}

}
