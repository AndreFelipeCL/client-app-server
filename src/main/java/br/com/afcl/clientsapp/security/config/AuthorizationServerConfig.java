package br.com.afcl.clientsapp.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author André Felipe C. Leite
 * @since 12/12/2020 - 18:56
 * @version 1.0
 */
@EnableAuthorizationServer
@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private final AuthenticationManager authenticationManager;

	@Value("${security.jwt.signing-key}")
	private String signingKey;

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(this.signingKey);
		return jwtAccessTokenConverter;
	}

	@Bean
	public TokenStore tokenStore(
			@Qualifier("jwtAccessTokenConverter") final JwtAccessTokenConverter jwtAccessTokenConverter
	) {
		return new JwtTokenStore(jwtAccessTokenConverter);
	}

	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		clients
				.inMemory()
				.withClient("client-app-ui")
				.secret("041bcbc6-091d-4c5e-a031-8c4d8f1a2a1d")
				.scopes("read", "write")
				.authorizedGrantTypes("password")
				.accessTokenValiditySeconds(1800)
		;
	}

	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.tokenStore(tokenStore(jwtAccessTokenConverter()))
				.accessTokenConverter(jwtAccessTokenConverter())
				.authenticationManager(this.authenticationManager)
		;
	}
}
