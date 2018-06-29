package com.fundoonotes.user.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fundoonotes.user.config.EmailUtil;
import com.fundoonotes.user.model.LoginDTO;
import com.fundoonotes.user.model.User;
import com.fundoonotes.user.model.UserDTO;
import com.fundoonotes.user.service.UserService;

@RestController
public class UserController {

	// private static final String loginURI = "http://localhost:8080/login";

	@Autowired
	private HttpHeaders httpHeaders;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private Mail mail;

	@Autowired
	private EmailUtil emailUtil;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Void> registerUser(@Valid @RequestBody UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		boolean result = userService.register(user);
		if (result) {
			httpHeaders.add("Location", ServletUriComponentsBuilder.fromCurrentRequest().toUriString().toString());
			String loginURI = ServletUriComponentsBuilder.fromCurrentRequest().toUriString().toString();
			mail.setTo(user.getEmail());
			mail.setSubject("Registration Successful");
			mail.setBody("<html><body>" + "<p>Please click on the below link to activate your account</p>" + "<h2>"
					+ loginURI + "</h2>" + "</body></html>");
			emailUtil.sendEmail(mail);
			return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<User> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
		User user = modelMapper.map(loginDTO, User.class);
		User loggedUser = userService.login(user);
		if (loggedUser != null) {
			return new ResponseEntity<User>(loggedUser, HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}

}
