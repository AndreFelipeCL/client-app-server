package br.com.afcl.clientsapp.endpoint.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author André Felipe C. Leite
 * @since 21/11/2020 - 14:00
 * @version 1.0
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class ServiceOrderDTO {
	private final String description;
	private final BigDecimal price;
	private final String providedAt;
	private final Long clientId;
}
