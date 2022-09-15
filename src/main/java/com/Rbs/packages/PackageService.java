package com.Rbs.packages;

import com.Rbs.customer.Customer;
import com.Rbs.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PackageService {

    private final PackageRepository packageRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public PackageService(PackageRepository packageRepository,CustomerRepository customerRepository){
        this.packageRepository = packageRepository;
        this.customerRepository = customerRepository;
    }
    public List<Package> getCustomers() {
        return packageRepository.findAll();
    }

    public void addNewPackage(String email) throws Exception {
        UUID customerID = customerRepository.findUUIDfromEmail(email);
        if(customerID == null) throw new Exception("No such Email is registered");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        Package newPackage = new Package(125, "upper lips + eyebrows",customerID,
                6,LocalDate.of(now.getYear(),now.getMonth(),now.getDayOfMonth()));
        packageRepository.save(newPackage);
    }
}
