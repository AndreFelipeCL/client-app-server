package br.com.afcl.clientsapp.domain.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author André Felipe C. Leite
 * @version 1.0
 * @since 08/11/2020 - 14:02
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
