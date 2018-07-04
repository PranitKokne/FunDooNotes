package com.fundoonotes.user.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fundoonotes.user.model.Mail;

@Configuration
@ComponentScan(basePackages = "com.fundoonotes")
public class SpringConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public Mail getMail() {
		return new Mail();
	}

	

}
