package com.Rbs.packages;

import com.Rbs.customer.Customer;
import com.Rbs.customer.CustomerService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/package")
public class PackageController {
    private final PackageService packageService;

    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping
    public List<Package> getStudents() {
        return packageService.getCustomers();
    }

    @PostMapping()
    @RequestMapping("/{email}")
    public ResponseEntity<String> registerNewPackage(@PathVariable("email") String email) throws Exception {
        packageService.addNewPackage(email);
        return ResponseEntity.status(HttpStatus.CREATED).body("New Package Created");
    }

}
