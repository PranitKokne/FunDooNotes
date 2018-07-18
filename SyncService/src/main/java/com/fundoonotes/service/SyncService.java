package com.fundoonotes.service;

import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundoonotes.repository.NoteRepository;
import com.fundoonotes.repository.UserRepository;

@Service
public class SyncService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	NoteRepository noteRepository;

	private static final Logger LOGGER = Logger.getLogger(SyncService.class);

	public void sendUserToSyncService(Map<String, Object> message) {
		LOGGER.info("****PROCESSING THE USER****");
		LOGGER.info("USER FROM SYNC SERVICE" + message.toString());
		String key = (String) message.get("KEY");
		String hkey = (String) message.get("HK");
		Object hvalue = message.get("object");
		String operation = (String) message.get("operation");
		if (operation.equalsIgnoreCase("insert")) {
			userRepository.save(key, hkey, hvalue);
		} else if (operation.equalsIgnoreCase("update")) {
			userRepository.save(key, hkey, hvalue);
		} else if (operation.equalsIgnoreCase("delete")) {
			userRepository.delete(key, hkey);
		}

	}

	public void sendNoteToSyncService(Map<String, Object> message) {
		LOGGER.info("****PROCESSING THE NOTE****");
		LOGGER.info("NOTE FROM SYNC SERVICE" + message.toString());
		String index = (String) message.get("index");
		String type = (String) message.get("type");
		String id = (String) message.get("id");
		Object document = message.get("document");
		String operation = (String) message.get("operation");
		if (operation.equalsIgnoreCase("index")) {
			noteRepository.insertNote(index, type, id, document);
		} else if (operation.equalsIgnoreCase("update")) {
			noteRepository.updateNote(index, type, id, document);
		} else if (operation.equalsIgnoreCase("delete")) {
			noteRepository.deleteNote(index, type, id);
		}
	}

}
