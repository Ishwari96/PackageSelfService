package com.abnamro.packageservice.repository;

import com.abnamro.packageservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * The Interface PackageService Repository.
 */
@Repository
public interface PackageServiceRepository extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {
}
