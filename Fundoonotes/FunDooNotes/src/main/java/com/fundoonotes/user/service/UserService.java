package com.fundoonotes.user.service;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fundoonotes.user.dao.UserDao;
import com.fundoonotes.user.model.LoginDTO;
import com.fundoonotes.user.model.User;
import com.fundoonotes.user.model.UserDTO;

@Service
public class UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserDao userDao;

	@Autowired
	private EmailService emailService;

	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}

	public boolean register(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		User dbUser = getByEmail(user.getEmail());
		if (dbUser == null) {
			String generatedPasswordHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
			user.setPassword(generatedPasswordHash);
			userDao.save(user);
			if (emailService.crateEmail(user.getEmail())) {
				return true;
			}
		}
		return false;
	}

	public boolean login(LoginDTO loginDTO) {
		User user = modelMapper.map(loginDTO, User.class);
		User dbUser = getByEmail(user.getEmail());
		if (dbUser != null && BCrypt.checkpw(user.getPassword(), dbUser.getPassword())) {
			return true;
		}
		return false;
	}
}
