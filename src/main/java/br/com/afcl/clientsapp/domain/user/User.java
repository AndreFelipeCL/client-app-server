package br.com.afcl.clientsapp.domain.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * @author André Felipe C. Leite
 * @since 12/12/2020 - 16:33
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
	@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
	private Long id;

	@Column(unique = true, nullable = false)
	@NotEmpty(message = "{validation.user.field.required.username}")
	private String username;

	@Column(nullable = false)
	@NotEmpty(message = "{validation.user.field.required.password}")
	private String password;

}
