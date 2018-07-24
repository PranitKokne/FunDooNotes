package com.fundoonotes.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fundoonotes.repository.RedisRepository;

@RestController
public class TestController {

	@Autowired
	private RedisRepository redisRepository;

	@RequestMapping(value = "fundoonotes/findall/{key}", method = RequestMethod.GET)
	public Map<String, Object> findAll(@PathVariable String key) {
		Map<String, Object> user = redisRepository.findAll(key);
		return user;
	}

	@RequestMapping(value = "fundoonotes/find/{key}/{id}", method = RequestMethod.GET)
	public Object find(@PathVariable String key, @PathVariable String id) {
		Object oneSpecificUser = redisRepository.find(key, id);
		return oneSpecificUser;
	}
}
