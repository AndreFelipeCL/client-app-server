package br.com.afcl.clientsapp.domain.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author André Felipe C. Leite
 * @version 1.0
 * @since 08/11/2020 - 17:53
 */
@Service
@RequiredArgsConstructor
public class ClientApplicationService {

	private final ClientRepository repository;

	public List<Client> findAll() {
		return repository.findAll();
	}

	public Client save(final Client client) {
		return repository.save(client);
	}

	public Optional<Client> findById(final Long id) {
		return repository.findById(id);
	}

	public void delete(final Long id) {
		repository.deleteById(id);
	}

	public Client update(final Client client) {
		return repository.save(client);
	}
}
