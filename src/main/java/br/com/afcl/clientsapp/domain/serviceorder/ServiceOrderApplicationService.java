package br.com.afcl.clientsapp.domain.serviceorder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author André Felipe C. Leite
 * @version 1.0
 * @since 08/11/2020 - 18:34
 */
@Service
@RequiredArgsConstructor
public class ServiceOrderApplicationService {
	private final ServiceOrderRepository repository;

	public List<ServiceOrder> findAll() {
		return repository.findAll();
	}

	public ServiceOrder save(final ServiceOrder serviceOrder){
		return repository.save(serviceOrder);
	}

	public List<ServiceOrder> findByFilter(final String name, final Integer month, final Integer year) {
		return repository.findByClientNameAndProvidedAtWithMonthAndYear(name, month, year);
	}
}
