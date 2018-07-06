package com.fundoonotes.service;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundoonotes.util.UserProducer;

@Service
public class GenericService {

	private static final Logger logger = Logger.getLogger(GenericService.class);

	@Autowired
	private UserProducer userProducer;

	public <T> void sendtoService(T obj) {
		// here the user and notes will be saved to the primary database as well
		// as a secondary database (redis:user::elastic search:notes)
		// so in order to save it to the secondary database pub/sub messaging model
		// is used.
		logger.info(obj);
		logger.info("object send to the producer");
		userProducer.sendToProducer(obj);
	}
}
