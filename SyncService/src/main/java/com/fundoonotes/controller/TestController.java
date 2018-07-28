package com.fundoonotes.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fundoonotes.repository.RedisRepository;

@RestController
public class TestController<T> {

	@Autowired
	private RedisRepository<T> redisRepository;

	@RequestMapping(value = "fundoonotes/findall/{key}", method = RequestMethod.GET)
	public Map<T, T> findAll(@PathVariable T key) {
		Map<T, T> user = redisRepository.findAll(key);
		return user;
	}

	@RequestMapping(value = "fundoonotes/find/{key}/{id}", method = RequestMethod.GET)
	public T find(@PathVariable T key, @PathVariable T id) {
		T oneSpecificUser = redisRepository.find(key, id);
		return oneSpecificUser;
	}
}
