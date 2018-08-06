package com.fundoonotes.read.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fundoonotes.read.repository.NoteRepository;
import com.fundoonotes.read.repository.UserRepository;
import com.fundoonotes.read.util.JwtTokenizer;
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

	@RequestMapping(value = "/fundoonotes/{key}/{hashKey}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUserById(@PathVariable("key") String key, @PathVariable("hashKey") String hashKey)
			throws JsonProcessingException {
		Object user = userRepository.getUserById(key, hashKey);
		if (user == null) {
			throw new ResourceNotFoundException(env.getProperty("resource.not.found"));
		}
		return new ResponseEntity<Object>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/getnotesofuser/{index}/{type}/search", method = RequestMethod.GET)
	public ResponseEntity<List<Map<String, Object>>> getNotesByUserId(@PathVariable Map<String, String> pathValues,
			@RequestParam("userId") String userId) {
		String index = pathValues.get("index");
		String type = pathValues.get("type");
		List<Map<String, Object>> output = noteRepository.getNotesByUserId(index, type, userId);
		if (output.size() == 0) {
			throw new ResourceNotFoundException(env.getProperty("resource.not.found"));
		}
		return new ResponseEntity<List<Map<String, Object>>>(output, HttpStatus.OK);
	}

	@RequestMapping(value = "/getnotes/{index}/{type}/search", method = RequestMethod.GET)
	public ResponseEntity<List<Map<String, Object>>> getNotesBySearching(@PathVariable Map<String, String> pathValues,
			@RequestParam("userId") String userId, @RequestParam("query_string") String queryString) {
		String index = pathValues.get("index");
		String type = pathValues.get("type");
		List<Map<String, Object>> output = noteRepository.getNotesBySearching(index, type, userId, queryString);
		if (output.size() == 0) {
			throw new ResourceNotFoundException(env.getProperty("resource.not.found"));
		}
		return new ResponseEntity<List<Map<String, Object>>>(output, HttpStatus.OK);
	}

	@RequestMapping(value = "/getlabeldetails/{index}/{type}/search", method = RequestMethod.GET)
	public ResponseEntity<List<Map<String, Object>>> getAllLabelNames(@PathVariable Map<String, String> pathValues,
			@RequestParam("userId") String userId) {
		String index = pathValues.get("index");
		String type = pathValues.get("type");
		List<Map<String, Object>> output = noteRepository.getLabelDetails(index, type, userId);
		if (output.isEmpty()) {
			throw new ResourceNotFoundException(env.getProperty("resource.not.found"));
		}
		return new ResponseEntity<List<Map<String, Object>>>(output, HttpStatus.OK);
	}

	@RequestMapping(value = "/gettoken", method = RequestMethod.GET)
	public ResponseEntity<String> getIdFromToken(HttpServletRequest servletRequest) {
		String token = servletRequest.getHeader("Authorization");
		String userId = JwtTokenizer.getUserIdFromToken(token);
		return new ResponseEntity<String>(userId, HttpStatus.OK);
	}

}
