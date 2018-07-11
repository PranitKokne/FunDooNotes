package com.fundoonotes.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fundoonotes.model.Note;
import com.fundoonotes.model.User;
import com.fundoonotes.repository.UserRepository;
import com.fundoonotes.service.TestService;

@RestController
public class TestController {

	@Autowired
	private TestService testService;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/fundoonotes/user", method = RequestMethod.POST)
	public void getUserModel(@RequestBody User user) {
		testService.sendUser(user);
	}

	@RequestMapping(value = "/fundoonotes/note", method = RequestMethod.POST)
	public void getNoteModel(@RequestBody Note note) {
		testService.sendNotes(note);
	}

	@RequestMapping(value = "fundoonotes/findall/{key}", method = RequestMethod.GET)
	public String findAll(@PathVariable String key) {
		String result = "";
		Map<String, Object> user = userRepository.findAll(key);
		for (Object o : user.values()) {
			result += o.toString();
		}
		return result;
	}

	@RequestMapping(value = "fundoonotes/find/{key}/{id}", method = RequestMethod.GET)
	public String find(@PathVariable String key, @PathVariable String id) {
		Object oneSpecificUser = userRepository.find(key, id);
		return oneSpecificUser.toString();
	}
}
