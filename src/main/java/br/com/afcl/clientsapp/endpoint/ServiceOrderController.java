package br.com.afcl.clientsapp.endpoint;

import br.com.afcl.clientsapp.domain.client.Client;
import br.com.afcl.clientsapp.domain.client.ClientApplicationService;
import br.com.afcl.clientsapp.domain.serviceorder.ServiceOrder;
import br.com.afcl.clientsapp.domain.serviceorder.ServiceOrderApplicationService;
import br.com.afcl.clientsapp.endpoint.dto.ServiceOrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * @author André Felipe C. Leite
 * @version 1.0
 * @since 08/11/2020 - 18:33
 */
@RestController
@RequestMapping("/service-order")
@RequiredArgsConstructor
public class ServiceOrderController {

	private final ServiceOrderApplicationService services;
	private final ClientApplicationService clientServices;

	@GetMapping("/all")
	public List<ServiceOrder> findAll() {
		return services.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServiceOrder save(@RequestBody @Valid final ServiceOrderDTO dto) {
		return clientServices.findById(dto.getClientId())
				.map(client -> {
					final ServiceOrder serviceOrder = ServiceOrder.from(dto, client);
					return services.save(serviceOrder);
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "{validation.response.client.notFound}"));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ServiceOrder update(@PathVariable @NotNull final Long id, @RequestBody @Valid final ServiceOrderDTO serviceOrderDTO) {
		return services.findById(id)
				.map(serviceOrderFound -> {
					Optional<Client> optionalClient = clientServices.findById(serviceOrderDTO.getClientId());
					serviceOrderFound.update(serviceOrderDTO, optionalClient.get());
					return services.update(serviceOrderFound);
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "{validation.response.serviceOrderDTO.notFound}"));
	}

	@GetMapping
	public List<ServiceOrder> findByFilter(@RequestParam(value = "clientName", required = false, defaultValue = "") final String clientName,
										   @RequestParam(value = "month", required = false) final Integer month,
										   @RequestParam(value = "year", required = false) final Integer year) {
		return services.findByFilter(clientName, month, year);
	}

}
