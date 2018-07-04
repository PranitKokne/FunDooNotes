package com.fundoonotes.user.service;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.fundoonotes.user.model.Mail;
import com.fundoonotes.util.EmailProducer;

@Service
public class EmailService {

	private static final Logger logger = Logger.getLogger(EmailService.class);

	@Autowired
	private Mail mail;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EmailProducer emailProducer;

	public boolean crateEmail(String email) {
		mail.setTo(email);
		mail.setSubject("Registration Successful");
		mail.setBody("<html><body>" + "<p>Please click on the below link to activate your account</p>" + "<h2>"
				+ "</h2>" + "</body></html>");

		emailProducer.produceEmail(mail);
		return true;

	}

	public boolean sendEmail(Mail mail) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			message.setRecipient(RecipientType.TO, new InternetAddress(mail.getTo(), true));
			message.setSubject(mail.getSubject());
			message.setContent(mail.getBody(), "text/html");
			logger.info("mail is ready");
			mailSender.send(message);
			logger.info("mail sent");
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}
}
