package com.gnolivos.users.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = { "com.gnolivos.*" })
@PropertySource("classpath:application.properties")
public class ApplicationProperties {
	
	@Value("${regular.expression.password}")
	private String regExpPassword;
	
	@Value("${regular.expression.email}")
	private String regExpEmail;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	/**
	 * @return the regExpPassword
	 */
	public String getRegExpPassword() {
		return regExpPassword;
	}

	/**
	 * @return the regExpEmail
	 */
	public String getRegExpEmail() {
		return regExpEmail;
	}
	
	
	
	
}
