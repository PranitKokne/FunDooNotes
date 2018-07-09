package com.fundoonotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundoonotes.model.User;
import com.fundoonotes.util.UserProducer;

@Service
public class UserService {
	
	@Autowired
	UserProducer userProducer;
	
	public void insertUserInRedis(User user) {
		userProducer.saveOrUpdate(user);
	}
	
	public void deleteInRedis(Long id) {
		userProducer.delete(id);
	}
	
	/*public void getAllInRedis() {
		
	}*/
}
