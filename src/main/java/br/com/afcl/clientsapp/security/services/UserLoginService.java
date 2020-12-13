package br.com.afcl.clientsapp.security.services;

import br.com.afcl.clientsapp.domain.user.User;
import br.com.afcl.clientsapp.domain.user.UserRepository;
import br.com.afcl.clientsapp.domain.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author André Felipe C. Leite
 * @since 13/12/2020 - 12:56
 * @version 1.0
 */
@RequiredArgsConstructor
@Service
public class UserLoginService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(Arrays.stream(UserRole.values()).map(UserRole::name).toArray(String[]::new))
				.build();
	}
}
