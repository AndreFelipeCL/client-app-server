package br.com.afcl.clientsapp.endpoint;

import br.com.afcl.clientsapp.domain.client.Client;
import br.com.afcl.clientsapp.domain.client.ClientApplicationServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author André Felipe C. Leite
 * @version 1.0
 * @since 08/11/2020 - 17:53
 */
@RequestMapping("/client")
@RestController
@RequiredArgsConstructor
public class ClientController {

	private final ClientApplicationServices services;

	@GetMapping("/all")
	public List<Client> findAll() {
		return services.findAll();
	}

	@GetMapping("/{id}")
	public Client findById(@PathVariable final Long id) {
		return services.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "{validation.response.client.notFound}"));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable final Long id) {
		services.findById(id)
				.map(r -> {
					services.delete(id);
					return Void.TYPE;
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "{validation.response.client.notFound}"));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client save(@RequestBody @Valid final Client client) {
		return services.save(client);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Client update(@PathVariable @NotNull final Long id, @RequestBody @Valid final Client client) {
		return services.findById(id)
				.map(clientFound -> {
					clientFound.update(client);
					return services.update(clientFound);
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "{validation.response.client.notFound}"));
	}

}
