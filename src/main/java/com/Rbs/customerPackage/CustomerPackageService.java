package com.Rbs.customerPackage;

import com.Rbs.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class CustomerPackageService {

    private final CustomerPackageRepository customerPackageRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerPackageService(CustomerPackageRepository customerPackageRepository, CustomerRepository customerRepository){
        this.customerPackageRepository = customerPackageRepository;
        this.customerRepository = customerRepository;
    }
    public List<CustomerPackage> getPackages() {
        return customerPackageRepository.findAll();
    }

    public void addNewPackage(String email) throws Exception {
        UUID customerID = customerRepository.findUUIDfromEmail(email);
        if(customerID == null) throw new Exception("No such Email is registered");
        LocalDate now = LocalDate.now();
        CustomerPackage newPackage = new CustomerPackage(customerID,1,6,now,now.plusMonths(12));
        customerPackageRepository.save(newPackage);
    }

    public int useOldPackage(UUID id) throws Exception {
        CustomerPackage p = customerPackageRepository.getByID(id);
        int servicesLeft = p.usePackage();
        if(servicesLeft == -1){
            throw new Exception("Cannot Use This Package");
        }
        customerPackageRepository.updateServicesCount(id,servicesLeft);
        return p.getServicesLeft();
    }

    public void deleteOldPackage(UUID id) throws Exception {
        customerPackageRepository.deleteOldPackage(id);
    }

    public List<CustomerPackage> getPackageForEmail(String email) {
        List<CustomerPackage> packageList = customerPackageRepository.findPackagesByEmail(email);
        return packageList;
    }
}
