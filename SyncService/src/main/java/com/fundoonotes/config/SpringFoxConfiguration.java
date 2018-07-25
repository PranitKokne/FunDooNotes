package com.fundoonotes.config;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:springfox-swagger.properties")
public class SpringFoxConfiguration {

	@Autowired
	private Environment env;

	@Value("${title}")
	private String title;

	@Value("${description}")
	private String description;

	@Value("${version}")
	private String version;

	@Value("${terms.of.service}")
	private String termOfService;

	@Value("${license}")
	private String license;

	@Value("${license.url}")
	private String licenseUrl;

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(env.getProperty("base.package"))).paths(PathSelectors.any())
				.build().apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo(
				title, description, version, termOfService, new Contact(env.getProperty("contact.name"),
						env.getProperty("contact.url"), env.getProperty("contact.email")),
				license, licenseUrl, Collections.emptyList());
	}
}
