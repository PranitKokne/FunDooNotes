package com.fundoonotes.user.config;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import com.fundoonotes.user.controller.Mail;

public class EmailUtil {

	private static final Logger logger = Logger.getLogger(EmailUtil.class);

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(Mail mail) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			
			message.setRecipient(RecipientType.TO, new InternetAddress(mail.getTo(), true));
			message.setSubject(mail.getSubject());
			message.setContent(mail.getBody(), "text/html");
			logger.info("mail is ready");
			mailSender.send(message);
			logger.info("mail sent");
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
