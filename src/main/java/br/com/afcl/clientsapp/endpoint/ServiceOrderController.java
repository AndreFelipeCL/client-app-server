package br.com.afcl.clientsapp.endpoint;

import br.com.afcl.clientsapp.domain.client.ClientApplicationServices;
import br.com.afcl.clientsapp.domain.serviceorder.ServiceOrder;
import br.com.afcl.clientsapp.domain.serviceorder.ServiceOrderApplicationService;
import br.com.afcl.clientsapp.endpoint.dto.ServiceOrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author André Felipe C. Leite
 * @version 1.0
 * @since 08/11/2020 - 18:33
 */
@RestController
@RequestMapping("/service-order")
@RequiredArgsConstructor
public class ServiceOrderController {

	private final ServiceOrderApplicationService service;
	private final ClientApplicationServices clientServices;

	@GetMapping("/all")
	public List<ServiceOrder> findAll() {
		return service.findAll();
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ServiceOrder save(@RequestBody final ServiceOrderDTO dto) {
		return clientServices.findById(dto.clientId())
				.map(client -> {
					final ServiceOrder serviceOrder = ServiceOrder.from(dto, client);
					return service.save(serviceOrder);
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "{validation.response.client.notFound}"));
	}

	@GetMapping
	public List<ServiceOrder> findByFilter(@RequestParam(value = "name", required = false, defaultValue = "") final String name,
										   @RequestParam(value = "month", required = false) final Integer month,
										   @RequestParam(value = "year", required = false) final Integer year){
		return service.findByFilter(name, month, year);
	}

}
