package com.oseevol.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageSourceConfig  {

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource  source = new ResourceBundleMessageSource ();
		source.setBasename("messages");
		source.setDefaultEncoding("UTF-8");
		return source;
	}
	
    
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
}
