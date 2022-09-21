package com.Rbs.customerPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/customer/{email}/package")
public class CustomerPackageController {
    private final CustomerPackageService customerPackageService;

    @Autowired
    public CustomerPackageController(CustomerPackageService customerPackageService) {
        this.customerPackageService = customerPackageService;
    }

    @PostMapping()
    @RequestMapping("/create-package")
    public ResponseEntity<String> registerNewPackage(@PathVariable("email") String email) throws Exception {
        customerPackageService.addNewPackage(email);
        return ResponseEntity.status(HttpStatus.CREATED).body("New Package Created");
    }

    @PutMapping
    @RequestMapping("/use/{package-uuid}")
    public ResponseEntity<String> useOldPackage(@PathVariable("package-uuid") UUID id) throws Exception {
        int i = customerPackageService.useOldPackage(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Package Used! You have "+i+" services left.");
    }

    @GetMapping
    public List<CustomerPackage> getPackageForEmail(@PathVariable("email") String email)throws Exception {
        return customerPackageService.getPackageForEmail(email);
    }

    @DeleteMapping
    @RequestMapping("/delete/{package-uuid}")
    public ResponseEntity<String> deleteOldPackage(@PathVariable("package-uuid") UUID id) throws Exception {
        customerPackageService.deleteOldPackage(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Package Deleted");
    }

}
