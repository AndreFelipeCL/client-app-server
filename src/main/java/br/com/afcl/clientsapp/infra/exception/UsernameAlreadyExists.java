package br.com.afcl.clientsapp.infra.exception;

import lombok.RequiredArgsConstructor;

/**
 * @author André Felipe C. Leite
 * @since 15/12/2020 - 15:25
 * @version 1.0
 */
public class UsernameAlreadyExists extends RuntimeException {
	public UsernameAlreadyExists(final String username) {
		super("Username já cadastrado na base de dados: " + username);
	}
}
