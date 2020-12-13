package br.com.afcl.clientsapp.endpoint;

import br.com.afcl.clientsapp.domain.user.User;
import br.com.afcl.clientsapp.domain.user.UserApplicationServices;
import br.com.afcl.clientsapp.infra.exception.UsernameAlreadyExists;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * @author André Felipe C. Leite
 * @since 12/12/2020 - 16:41
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserApplicationServices service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody @Valid final User newUser){
		try {
			service.save(newUser);
		} catch (UsernameAlreadyExists usernameAlreadyExists){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, usernameAlreadyExists.getMessage());
		}
	}
}
