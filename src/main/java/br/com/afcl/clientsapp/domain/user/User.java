package br.com.afcl.clientsapp.domain.user;

import lombok.*;

import javax.persistence.*;

/**
 * @author André Felipe C. Leite
 * @since 12/12/2020 - 16:33
 * @version 1.0
 */
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
	@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
	private Long id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

}
