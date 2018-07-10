package com.fundoonotes.service;

import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundoonotes.repository.UserRepository;

@Service
public class SyncService {

	@Autowired
	UserRepository userRepository;
	
	private static final Logger LOGGER = Logger.getLogger(SyncService.class);

	public void save(Map<String, Object> message) {
		
		String key = (String) message.get("KEY");
		Long hkey = Long.valueOf((String)message.get("hkey"));
		Object hvalue = message.get("object");
		LOGGER.info("call repo save method");
		userRepository.save(key, hkey, hvalue);
		LOGGER.info("call finish");
	}

	public void update() {

	}

	public void delete() {

	}
}
