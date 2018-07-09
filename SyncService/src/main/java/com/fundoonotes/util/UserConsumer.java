package com.fundoonotes.util;

import org.jboss.logging.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fundoonotes.config.RabbitMQConfig;
import com.fundoonotes.model.User;
import com.fundoonotes.repository.UserRepository;

@Component
public class UserConsumer {

	private static final Logger logger = Logger.getLogger(UserConsumer.class);

	@Autowired
	UserRepository userRepository;
	
	// @RabbitListener(queues = RabbitMQConfig.queueName, containerFactory =
	// "containerFactory")

	@RabbitListener(queues = RabbitMQConfig.queueNameOne, containerFactory = "containerFactory")
	public void saveOrUpdateUser(User user) {
		userRepository.save(user);
	}

	@RabbitListener(queues = RabbitMQConfig.queueNameThree, containerFactory = "containerFactory")
	public void deleteUser(Long id) {
		userRepository.delete(id);
	}

	
}
