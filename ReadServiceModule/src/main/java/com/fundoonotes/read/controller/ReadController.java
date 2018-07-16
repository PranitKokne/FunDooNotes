package com.fundoonotes.read.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fundoonotes.read.repository.NoteRepository;
import com.fundoonotes.read.repository.UserRepository;

@RestController
public class ReadController {

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "todo/{index}/{type}/{id}", method = RequestMethod.GET)
	public Map<String, Object> getNoteById(@PathVariable Map<String, String> pathValues) {
		String index = pathValues.get("index");
		String type = pathValues.get("type");
		String id = pathValues.get("id");
		return noteRepository.getNoteById(index, type, id);
	}

	@RequestMapping(value = "/fundoonotes/{key}/{hashKey}", method = RequestMethod.GET)
	public Object getUserById(@PathVariable("key") String key,@PathVariable("hashKey") String hashKey) {
		return userRepository.getUserById(key, hashKey);
	}

}
