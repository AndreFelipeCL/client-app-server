package br.com.afcl.clientsapp.domain.serviceorder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author André Felipe C. Leite
 * @version 1.0
 * @since 08/11/2020 - 14:04
 */
@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
}
