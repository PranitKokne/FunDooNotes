package com.fundoonotes.user.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import com.fundoonotes.user.model.Mail;

@Configuration
@ComponentScan(basePackages = "com.fundoonotes")
public class SpringConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public HttpHeaders httpHeaders() {
		return new HttpHeaders();
	}

	@Bean
	public Mail getMail() {
		return new Mail();
	}

	@Bean
	public EmailUtil emailUtil() {
		return new EmailUtil();
	}

}
