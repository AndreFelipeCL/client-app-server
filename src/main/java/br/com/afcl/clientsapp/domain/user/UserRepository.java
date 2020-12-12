package br.com.afcl.clientsapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author André Felipe C. Leite
 * @since 12/12/2020 - 16:37
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
