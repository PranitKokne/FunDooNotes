package com.fundoonotes.user.config;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.jboss.logging.Logger;

public class EmailUtil {

	private static final Logger logger = Logger.getLogger(EmailUtil.class);

	public static void sendEmail(Session session, String toEmail, String subject, String body) {

		MimeMessage email = new MimeMessage(session);

		try {
			// set header content
			email.addHeader("Content-type", "text/html; charset=UTF-8");
			email.addHeader("format", "flowd");
			email.addHeader("Content-Transfer-Encoding", "8bit");

			email.setFrom(new InternetAddress("pranitkokne2018@gmail.com", "No-Reply-PK"));
			email.setReplyTo(InternetAddress.parse("pranitkokne2018@gmail.com", false));

			email.setSubject(subject, "UTF-8");
			email.setText(body, "UTF-8");
			email.setSentDate(new Date());

			email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			logger.info("message is ready");

			Transport.send(email);

			logger.info("email sent successfully");

		} catch (MessagingException | UnsupportedEncodingException e) {
			logger.info("exception occured while sending a mail");
			e.printStackTrace();
		}

	}

	public static Session getSession() {

		final String fromEmail = "fundoo8080@gmail.com";
		final String password = "gokupubg";

		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Authenticator authenticator = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getInstance(properties, authenticator);
		return session;
	}

}
