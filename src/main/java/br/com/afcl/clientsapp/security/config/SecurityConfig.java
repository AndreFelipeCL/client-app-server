package br.com.afcl.clientsapp.security.config;

import br.com.afcl.clientsapp.security.services.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author André Felipe C. Leite
 * @since 12/12/2020 - 18:10
 * @version 1.0
 */
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserLoginService userLoginService;

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	public void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userLoginService)
			.passwordEncoder(passwordEncoder())
		;
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
