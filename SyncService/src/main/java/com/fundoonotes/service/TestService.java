package com.fundoonotes.service;

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
		userProducer.sendToUserProducer(user);
		logger.info("user send successfully");
	}

	public void sendNotes(Note note) {
		logger.info("sending note to the producer");
		noteProducer.sendToNoteProducer(note);
		logger.info("note send successfully");
	}
}
