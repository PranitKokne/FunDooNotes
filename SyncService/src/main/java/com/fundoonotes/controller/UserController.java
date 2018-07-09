package com.fundoonotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fundoonotes.model.User;
import com.fundoonotes.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "redis/insert", method = RequestMethod.POST)
	public void insertUser(@RequestBody User user) {
		userService.insertUserInRedis(user);
	}

	@RequestMapping(value = "redis/update", method = RequestMethod.POST)
	public void updateUser(@RequestBody User user) {
		userService.insertUserInRedis(user);
	}

	@RequestMapping(value = "redis/delete", method = RequestMethod.DELETE)
	public void deleteUser(@RequestBody User user) {
		userService.deleteInRedis(user.getId());
	}

}
