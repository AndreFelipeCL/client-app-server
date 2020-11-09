package br.com.afcl.clientsapp.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * @author André Felipe C. Leite
 * @version 1.0
 * @since 08/11/2020 - 20:07
 */
@Configuration
public class i18nConfig {

	@Bean
	public MessageSource messageSource(){
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages/messages");
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
		messageSource.setDefaultLocale(Locale.getDefault());
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean validatorFactoryBean(final MessageSource messageSource){
		final LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(messageSource);
		return localValidatorFactoryBean;
	}

}
