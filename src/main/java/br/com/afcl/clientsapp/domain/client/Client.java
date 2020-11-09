package br.com.afcl.clientsapp.domain.client;

import br.com.afcl.clientsapp.domain.serviceorder.ServiceOrder;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author André Felipe C. Leite
 * @version 1.0
 * @since 08/11/2020 _ 13:39
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {

	@Id
	@SequenceGenerator(name = "client_id_seq", sequenceName = "client_id_seq", allocationSize = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_seq")
	private Long id;

	@Column(nullable = false, length = 150)
	@NotEmpty(message = "{validation.field.required.name}")
	private String name;

	@Column(nullable = false, length = 11)
	@NotNull(message = "{validation.field.required.cpf}")
	@CPF(message = "{validation.field.invalid.cpf}")
	private String cpf;

	@Column(updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate createdDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client", fetch = FetchType.LAZY)
	private Set<ServiceOrder> serviceOrders = new HashSet<>();

	@PrePersist
	public void prePersist(){
		this.setCreatedDate(LocalDate.now());
	}

	public void update(final Client client) {
		this.cpf = client.getCpf();
		this.name = client.getName();
	}
}
