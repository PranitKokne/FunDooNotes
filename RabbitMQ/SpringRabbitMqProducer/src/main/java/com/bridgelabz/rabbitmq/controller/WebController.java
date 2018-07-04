package com.bridgelabz.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.rabbitmq.model.Producer;

@RestController
public class WebController {

	@Autowired
	Producer producer;

	@RequestMapping(value = "/send")
	public String sendMsg(@RequestParam("msg") String msg) {
		producer.produceMsg(msg);
		return "done";
	}
}
