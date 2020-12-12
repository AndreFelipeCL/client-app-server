package br.com.afcl.clientsapp.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author André Felipe C. Leite
 * @since 12/12/2020 - 16:42
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class UserApplicationServices {

	private final UserRepository repository;

	public void save(final User newUser) {
		repository.save(newUser);
	}

}
