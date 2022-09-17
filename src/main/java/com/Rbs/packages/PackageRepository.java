package com.Rbs.packages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface PackageRepository extends JpaRepository<Package,Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Package set services_left = :servicesLeft WHERE id = :id")
    void updateServicesCount(UUID id, int servicesLeft);

    @Query(value = "SELECT p FROM Package p WHERE p.id = :id")
    Package getByID(UUID id);

    @Query(value = "SELECT p FROM Package p LEFT JOIN Customer c on p.customerID = c.id WHERE c.email = :email")
    List<Package> findPackagesByEmail(String email);


    @Modifying
    @Transactional
    @Query(value = "DELETE Package p WHERE p.id = :id")
    void deleteOldPackage(UUID id);
}
