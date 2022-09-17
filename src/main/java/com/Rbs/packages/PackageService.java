package com.Rbs.packages;

import com.Rbs.customer.CustomerRepository;
import com.sun.xml.bind.v2.runtime.reflect.Lister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PackageService {

    private final PackageRepository packageRepository;
    private final CustomerRepository customerRepository;

    private final int NEW_PACKAGE_AMOUNT = 6;

    @Autowired
    public PackageService(PackageRepository packageRepository,CustomerRepository customerRepository){
        this.packageRepository = packageRepository;
        this.customerRepository = customerRepository;
    }
    public List<Package> getPackages() {
        return packageRepository.findAll();
    }

    public void addNewPackage(String email) throws Exception {
        UUID customerID = customerRepository.findUUIDfromEmail(email);
        if(customerID == null) throw new Exception("No such Email is registered");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        Package newPackage = new Package(PackageType.E,customerID,
                NEW_PACKAGE_AMOUNT,LocalDate.of(now.getYear(),now.getMonth(),now.getDayOfMonth()));
        packageRepository.save(newPackage);
    }

    public int useOldPackage(UUID id) throws Exception {
        Package p = packageRepository.getByID(id);
        int servicesLeft = p.usePackage();
        if(servicesLeft == -1){
            throw new Exception("Cannot Use This Package");
        }
        packageRepository.updateServicesCount(id,servicesLeft);
        return p.getServicesLeft();
    }

    public void deleteOldPackage(UUID id) throws Exception {
        packageRepository.deleteOldPackage(id);
    }

    public List<Package> getPackageForEmail(String email) {
        List<Package> packageList = packageRepository.findPackagesByEmail(email);
        return packageList;
    }
}
