package com.fundoonotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fundoonotes.model.Note;
import com.fundoonotes.model.User;
import com.fundoonotes.service.TestService;

@RestController
public class TestController {

	@Autowired
	private TestService testService;

	@RequestMapping(value = "/fundoonotes/user", method = RequestMethod.POST)
	public void getUserModel(@RequestBody User user) {
		testService.sendUser(user);
	}

	@RequestMapping(value = "/fundoonotes/note", method = RequestMethod.POST)
	public void getNoteModel(@RequestBody Note note) {
		testService.sendNotes(note);
	}
}
