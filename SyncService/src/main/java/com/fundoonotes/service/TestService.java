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
		message.put("operation", user.getOperation());
		message.put("object", user);
		message.put("HK", user.getId());
		message.put("KEY", "user");
		userProducer.sendToUserProducer(message);
		logger.info("user send successfully");
	}

	public void sendNotes(Note note) {
		logger.info("sending note to the producer");
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("operation", note.getOperation());
		message.put("index", "fundoonotes");
		message.put("type", "note");
		message.put("id", String.valueOf(note.getId()));
		message.put("document", note);
		noteProducer.sendToNoteProducer(message);
		logger.info("note send successfully");
	}
}
