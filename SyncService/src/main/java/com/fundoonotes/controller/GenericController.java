package com.fundoonotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fundoonotes.service.GenericService;

@RestController
public class GenericController {

	@Autowired
	private GenericService gservice;

	@RequestMapping(value = "/redis/insert", method = RequestMethod.POST)
	public <T> void insertIntoRedis(@RequestBody T obj) {
		// send the object to the service of user or note module
		gservice.sendtoService(obj);
	}
}
