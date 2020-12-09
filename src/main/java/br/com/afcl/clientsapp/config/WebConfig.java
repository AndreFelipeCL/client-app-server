package br.com.afcl.clientsapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author André Felipe C. Leite
 * @since 08/12/2020 - 21:32
 * @version 1.0
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig {

	@Bean
	public List<String> allowedOrigins() {
		return Collections.unmodifiableList(Arrays.asList("http://localhost:4200"));
	}

	@Bean
	public List<String> allowedHeaders() {
		return Collections.unmodifiableList(Arrays.asList("*"));
	}

	@Bean
	public List<String> allowedMethods() {
		return Collections.unmodifiableList(Arrays.asList("*"));
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean(
			@Qualifier("allowedOrigins") final List<String> allowedOrigins,
			@Qualifier("allowedHeaders") final List<String> allowedHeaders,
			@Qualifier("allowedMethods") final List<String> allowedMethods
	) {
		final CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(allowedOrigins);
		corsConfiguration.setAllowedHeaders(allowedHeaders);
		corsConfiguration.setAllowedMethods(allowedMethods);
		corsConfiguration.setAllowCredentials(true);

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);

		final CorsFilter corsFilter = new CorsFilter(source);
		final FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean = new FilterRegistrationBean<>(corsFilter);
		corsFilterFilterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return corsFilterFilterRegistrationBean;
	}
}
