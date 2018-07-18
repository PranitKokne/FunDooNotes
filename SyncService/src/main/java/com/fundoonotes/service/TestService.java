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

	private static final Logger LOGGER = Logger.getLogger(TestService.class);

	public void sendUser(User user) {
		LOGGER.info("SENDING USER TO THE PRODUCER");
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("KEY", "user");
		message.put("HK", user.getId());
		message.put("object", user);
		message.put("operation", user.getOperation());
		userProducer.sendUserToProducer(message);
		LOGGER.info("USER SEND SUCCESSFULLY");
	}

	public void sendNotes(Note note) {
		LOGGER.info("SENDING NOTE TO THE PRODUCER");
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("index", "fundoonotes");
		message.put("type", "note");
		message.put("id", String.valueOf(note.getId()));
		message.put("document", note);
		message.put("operation", note.getOperation());
		noteProducer.sendNoteToProducer(message);
		LOGGER.info("NOTE SEND SUCCESSFULLY");
	}
}
