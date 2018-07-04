package com.fundoonotes.user.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fundoonotes.user.model.LoginDTO;
import com.fundoonotes.user.model.UserDTO;
import com.fundoonotes.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO userDTO) {
		boolean result = userService.register(userDTO);
		if (result) {
			return new ResponseEntity<String>("Registration Successful", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Registration Failed", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Void> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
		boolean result = userService.login(loginDTO);
		if (result) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
}
