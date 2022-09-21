package com.Rbs.packages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PackageRepository extends JpaRepository<Package,Long> {
    @Query(nativeQuery = true)
    PackageMap getPackagesDetailed();
}
