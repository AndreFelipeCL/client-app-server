package br.com.afcl.clientsapp.endpoint.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author André Felipe C. Leite
 * @since 21/11/2020 - 14:00
 * @version 1.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceOrderDTO {

	@NotEmpty(message = "{validation.serviceOrder.field.required.description}")
	private String description;

	@NotEmpty(message = "{validation.serviceOrder.field.required.price}")
	private String price;

	@NotEmpty(message = "{validation.serviceOrder.field.required.providedAt}")
	private String providedAt;

	@NotNull(message = "{validation.serviceOrder.field.required.clientId}")
	private Long clientId;
}
