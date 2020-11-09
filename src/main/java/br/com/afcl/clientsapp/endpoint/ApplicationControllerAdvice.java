package br.com.afcl.clientsapp.endpoint;

import br.com.afcl.clientsapp.infra.ApiError;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author André Felipe C. Leite
 * @version 1.0
 * @since 08/11/2020 - 20:39
 */
@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleValidationErrors(final MethodArgumentNotValidException exception){
		final BindingResult bindingResult = exception.getBindingResult();
		final List<String> errors = bindingResult.getAllErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.toList());

		return new ApiError(errors);
	}

	@ExceptionHandler(ResponseStatusException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity handleResponseStatusException(final ResponseStatusException exception){
		final String exceptionMessage = exception.getMessage();
		final HttpStatus status = exception.getStatus();

		final ApiError apiError = new ApiError(exceptionMessage);
		return new ResponseEntity(apiError, status);
	}

}
