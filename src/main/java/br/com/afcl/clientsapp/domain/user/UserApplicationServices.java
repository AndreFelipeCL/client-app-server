package br.com.afcl.clientsapp.domain.user;

import br.com.afcl.clientsapp.infra.exception.UsernameAlreadyExists;
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

	public void save(final User newUser) throws UsernameAlreadyExists {
		if(repository.findByUsername(newUser.getUsername()).isPresent()){
			throw new UsernameAlreadyExists(newUser.getUsername());
		}
		repository.save(newUser);
	}

}
