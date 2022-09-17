package com.Rbs.packages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/customer/{email}/package")
public class PackageController {
    private final PackageService packageService;

    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping()
    @RequestMapping("/create-package")
    public ResponseEntity<String> registerNewPackage(@PathVariable("email") String email) throws Exception {
        packageService.addNewPackage(email);
        return ResponseEntity.status(HttpStatus.CREATED).body("New Package Created");
    }

    @PutMapping
    @RequestMapping("/use/{package-uuid}")
    public ResponseEntity<String> useOldPackage(@PathVariable("package-uuid") UUID id) throws Exception {
        int i = packageService.useOldPackage(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Package Used! You have "+i+" services left.");
    }

    @GetMapping
    public List<Package> getPackageForEmail(@PathVariable("email") String email)throws Exception {
        return packageService.getPackageForEmail(email);
    }

    @DeleteMapping
    @RequestMapping("/delete/{package-uuid}")
    public ResponseEntity<String> deleteOldPackage(@PathVariable("package-uuid") UUID id) throws Exception {
        packageService.deleteOldPackage(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Package Deleted");
    }

}
