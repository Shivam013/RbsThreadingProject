package com.Rbs.packages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/package-details")
public class PackageController {
    private PackageRepository packageRepository;
    @Autowired
    public PackageController(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

   @GetMapping
   @RequestMapping(path = "/all")
    public String getPackageDescs(){
        PackageMap pm= packageRepository.getPackagesDetailed();
        return pm.toString();
    }
}
