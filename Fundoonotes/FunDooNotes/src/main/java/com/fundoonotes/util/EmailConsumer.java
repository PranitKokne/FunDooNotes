package com.fundoonotes.util;

import org.jboss.logging.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fundoonotes.user.config.RabbitMQConfig;
import com.fundoonotes.user.model.Mail;
import com.fundoonotes.user.service.EmailService;

@Component
public class EmailConsumer {

	private static final Logger logger = Logger.getLogger(EmailConsumer.class);

	@Autowired
	private EmailService emailService;

	@RabbitListener(queues = RabbitMQConfig.queueName, containerFactory = "containerFactory")
	public void receiveEmail(Mail mail) {
		logger.info("message received from the queue");
		emailService.sendEmail(mail);
		logger.info("email sent to the user");

	}
}
