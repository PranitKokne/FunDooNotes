package com.fundoonotes.user.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundoonotes.user.dao.UserDao;
import com.fundoonotes.user.model.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}

	public boolean register(User user) {
		User dbUser = getByEmail(user.getEmail());
		if (dbUser == null) {
			String generatedPasswordHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
			user.setPassword(generatedPasswordHash);
			userDao.save(user);
			return true;
		}
		return false;
	}

	public User login(User user) {
		User dbUser = getByEmail(user.getEmail());
		if (dbUser != null && BCrypt.checkpw(user.getPassword(), dbUser.getPassword())) {
			dbUser.setPassword(user.getPassword());
			return dbUser;
		}
		return null;
	}
}
