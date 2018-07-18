package com.fundoonotes.read.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fundoonotes.read.repository.NoteRepository;
import com.fundoonotes.read.repository.UserRepository;
import com.fundoonotes.read.util.ResourceNotFoundException;

@RestController
@PropertySource({ "classpath:exception.properties" })
public class ReadController {

	@Autowired
	private Environment env;

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/todo/{index}/{type}/{id}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getNoteById(@PathVariable Map<String, String> pathValues) {
		String index = pathValues.get("index");
		String type = pathValues.get("type");
		String id = pathValues.get("id");
		Map<String, Object> note = noteRepository.getNoteById(index, type, id);
		if (note == null) {
			throw new ResourceNotFoundException(env.getProperty("resource.not.found"));
		}
		return new ResponseEntity<>(note, HttpStatus.OK);
	}

	@RequestMapping(value = "/fundoonotes/{key}/{hashKey}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUserById(@PathVariable("key") String key,
			@PathVariable("hashKey") String hashKey) {
		Object user = userRepository.getUserById(key, hashKey);
		if (user == null) {
			throw new ResourceNotFoundException(env.getProperty("resource.not.found"));
		}
		return new ResponseEntity<Object>(user, HttpStatus.OK);
	}

}
