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

	public void sendToSyncService(Map<String,Object> message) {
		String key = (String) message.get("KEY");
		String hkey = (String) message.get("HK");
		Object hvalue = message.get("object");
		String operation = (String) message.get("operation");
		if(operation.equalsIgnoreCase("insert")) {
			userRepository.save(key, hkey, hvalue);
		}else if(operation.equalsIgnoreCase("update")) {
			userRepository.save(key, hkey, hvalue);
		}else if(operation.equalsIgnoreCase("delete")) {
			userRepository.delete(key, hkey);
		}
		
	}
	
	/*public void save(Map<String, Object> message) {
		
		String key = (String) message.get("KEY");
		String hkey = (String) message.get("HK");
		Object hvalue = message.get("object");
		LOGGER.info("calling repo save method");
		userRepository.save(key,hkey,hvalue);
	}

	public void update() {

	}

	public void delete() {

	}*/
}
