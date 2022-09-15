package com.Rbs.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query(value = "SELECT c FROM Customer c WHERE c.email = :email", nativeQuery = false)
    Optional<Customer> findCustomerByEmail(String email);

    @Query("select c.id from Customer c where c.email = :email")
    UUID findUUIDfromEmail(String email);



}
