package br.com.afcl.clientsapp.domain.serviceorder;

import br.com.afcl.clientsapp.domain.client.Client;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author André Felipe C. Leite
 * @version 1.0
 * @since 08/11/2020 - 13:53
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_id_seq")
	@SequenceGenerator(name = "service_id_seq", sequenceName = "service_id_seq", allocationSize = 10)
	private Long id;

	@Column(nullable = false)
	private String description;

	@ManyToOne
	private Client client;

	@Column(nullable = false)
	private BigDecimal price;
}
