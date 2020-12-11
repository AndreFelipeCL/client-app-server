package br.com.afcl.clientsapp.domain.serviceorder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author André Felipe C. Leite
 * @version 1.0
 * @since 08/11/2020 - 14:04
 */
@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

	@Query("SELECT s FROM ServiceOrder s " +
			"INNER JOIN Client c ON s.client.id = c.id " +
			"WHERE UPPER( c.name ) LIKE CONCAT('%', UPPER( :clientName ), '%') " +
			"AND MONTH(s.providedAt) = :month " +
			"AND YEAR(s.providedAt) = :year")
	List<ServiceOrder> findByClientNameAndProvidedAtWithMonthAndYear(@Param("clientName") String clientName, @Param("month") Integer month, @Param("year") Integer year);

}
