package br.com.afcl.clientsapp.endpoint;

import br.com.afcl.clientsapp.domain.serviceorder.ServiceOrder;
import br.com.afcl.clientsapp.domain.serviceorder.ServiceOrderApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author André Felipe C. Leite
 * @version 1.0
 * @since 08/11/2020 - 18:33
 */
@RequestMapping("/serviceOrder")
@RestController
@RequiredArgsConstructor
public class ServiceOrderController {
	private final ServiceOrderApplicationService service;

	@GetMapping("/all")
	public List<ServiceOrder> findAll(){
		return service.findAll();
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ServiceOrder save(@RequestBody final ServiceOrder serviceOrder){
		return service.save(serviceOrder);
	}
}
