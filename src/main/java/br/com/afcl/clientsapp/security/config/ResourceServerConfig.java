package br.com.afcl.clientsapp.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author André Felipe C. Leite
 * @since 12/12/2020 - 18:56
 * @version 1.0
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(final HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/actuator/**").permitAll()
				.antMatchers("/user/**").permitAll()
				.antMatchers("/client/**", "/service-order/**").authenticated()
			.anyRequest().denyAll()
		;
	}
}
