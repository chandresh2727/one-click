package com.ecommerce.E_commerce.repository;

import com.ecommerce.E_commerce.entity.Selection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface SelectionRepository extends JpaRepository<Selection, Long> {

    List<Selection> findByClient_ClientName(String clientName);
    List<Selection> findByConfirmedDate(LocalDate date);

}
