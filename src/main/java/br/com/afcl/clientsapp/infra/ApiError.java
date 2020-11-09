package br.com.afcl.clientsapp.infra;

import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author André Felipe C. Leite
 * @version 1.0
 * @since 08/11/2020 - 20:46
 */
@Getter
public class ApiError {
	private final Set<String> errors = new HashSet<>();

	public ApiError(final String error) {
		this.errors.add(error);
	}

	public ApiError(final List<String> errors) {
		this.errors.addAll(errors);
	}

}
