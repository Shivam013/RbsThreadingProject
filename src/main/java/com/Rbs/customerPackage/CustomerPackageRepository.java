package com.Rbs.customerPackage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerPackageRepository extends JpaRepository<CustomerPackage,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE CustomerPackage set services_left = :servicesLeft WHERE id = :id")
    void updateServicesCount(UUID id, int servicesLeft);

    @Query(value = "SELECT p FROM CustomerPackage p WHERE p.id = :id")
    CustomerPackage getByID(UUID id);

    @Query(value = "SELECT p FROM CustomerPackage p LEFT JOIN Customer c on p.customerId = c.id WHERE c.email = :email")
    List<CustomerPackage> findPackagesByEmail(String email);


    @Modifying
    @Transactional
    @Query(value = "DELETE CustomerPackage p WHERE p.id = :id")
    void deleteOldPackage(UUID id);
}
