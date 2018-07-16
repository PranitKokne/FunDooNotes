package com.fundoonotes.read.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfiguration {

	
	private static final String TITLE = "Read Service API Documentation";
	private static final String DESCRIPTION = "This application demonstrates how to RETRIEVE the data from various kinds of PERSISTENT store";
	private static final String VERSION = "1.0.0";
	private static final String TERMSOFSERVICE = "Terms Of Service";
	private static final String LICENCE = "Apache License";
	private static final String LICENCEURL = "https://en.wikipedia.org/wiki/Apache_License";
	
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.fundoonotes.read"))
				.paths(PathSelectors.any()).build().apiInfo(getApiInfo());
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(TITLE, DESCRIPTION, VERSION, TERMSOFSERVICE,
				new Contact("PranitKokne", "https://www.google.com/", "pranitkokne2018@gmail.com"), LICENCE, LICENCEURL,
				Collections.emptyList());
	}
}
