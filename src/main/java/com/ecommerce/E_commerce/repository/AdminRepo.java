package com.ecommerce.E_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.E_commerce.entity.Admin;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface AdminRepo extends JpaRepository<Admin,Long> {

    boolean existsByAdminName(@Param("admin_name") String admin_name);

    boolean existsByAdminPassword(@Param("admin_password") String admin_password);

    List<Admin> findByAdminName(@Param("admin_name") String admin_name);

    void deleteByAdminName(@Param("admin_name") String admin_name);

}
