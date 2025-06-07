package com.ecommerce.E_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.E_commerce.entity.Client;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ClientRepo extends JpaRepository<Client,Long> {

    boolean existsByClientName(@Param("client_name") String name);

    Optional<Client> findByClientName(String clientName);

//    List<Client> findByAdmin_Id(Long adminId);
//    Client findByNameEmailAndPhoneNumber(@Param("client_name") String name, @Param("client_email_id") String emailId,@Param("client_phone_number") Long phoneNumber);

}
