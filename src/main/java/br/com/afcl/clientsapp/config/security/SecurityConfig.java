package br.com.afcl.clientsapp.config.security;

import br.com.afcl.clientsapp.domain.user.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static br.com.afcl.clientsapp.domain.user.UserRole.ADMIN;

/**
 * @author André Felipe C. Leite
 * @since 12/12/2020 - 18:10
 * @version 1.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.
				inMemoryAuthentication()
				.withUser("master")
				.password("master")
				.roles(ADMIN.name())
		;
	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.cors()
		.and()
			/**
			 * Definindo que nossa aplicacao nao guarda sessao. A sessao da aplicacao será gerenciada via Token
			 * com tempo de expiracao. Caso o token precise ser renovado, usa-se o <i>RefreshToken</i> para tal.
			 */
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		;
	}
}
