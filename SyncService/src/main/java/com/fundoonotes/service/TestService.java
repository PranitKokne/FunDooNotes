package com.fundoonotes.service;

import java.util.HashMap;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundoonotes.model.Note;
import com.fundoonotes.model.User;
import com.fundoonotes.util.NoteProducer;
import com.fundoonotes.util.UserProducer;

@Service
public class TestService {

	@Autowired
	private UserProducer userProducer;

	@Autowired
	private NoteProducer noteProducer;

	private static final Logger logger = Logger.getLogger(TestService.class);

	public void sendUser(User user) {
		logger.info("sending user to the producer");
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("type", "redis");
		message.put("operation", "insert");
		message.put("object", user);
		message.put("HK", user.getId());
		message.put("KEY", "USER");
		userProducer.sendToUserProducer(message);
		logger.info("user send successfully");
	}

	public void sendNotes(Note note) {
		logger.info("sending note to the producer");
		noteProducer.sendToNoteProducer(note);
		logger.info("note send successfully");
	}
}