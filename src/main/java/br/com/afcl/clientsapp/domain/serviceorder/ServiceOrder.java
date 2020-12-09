package br.com.afcl.clientsapp.domain.serviceorder;

import br.com.afcl.clientsapp.domain.client.Client;
import br.com.afcl.clientsapp.endpoint.dto.ServiceOrderDTO;
import br.com.afcl.clientsapp.util.LocalDateParser;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author André Felipe C. Leite
 * @version 1.0
 * @since 08/11/2020 - 13:53
 */
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_id_seq")
	@SequenceGenerator(name = "service_id_seq", sequenceName = "service_id_seq", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	private String description;

	@ManyToOne
	private Client client;

	@Column(nullable = false)
	private BigDecimal price;

	@Column(nullable = false)
	private LocalDate providedAt;

	public static ServiceOrder from(final ServiceOrderDTO dto, final Client client) {
		return ServiceOrder.builder()
				.description(dto.getDescription())
				.price(new BigDecimal(dto.getPrice()))
				.providedAt(LocalDateParser.of(dto.getProvidedAt()))
				.client(client)
				.build();
	}

	public void update(final @Valid ServiceOrderDTO serviceOrder, final Client client) {
		this.client = client;
		this.description = serviceOrder.getDescription();
		this.price = new BigDecimal(serviceOrder.getPrice());
		this.providedAt = LocalDateParser.of(serviceOrder.getProvidedAt());
	}

}
