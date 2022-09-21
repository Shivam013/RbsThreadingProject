package com.Rbs.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getStudents() {
        return customerService.getCustomers();
    }

    @PostMapping()
    @RequestMapping("/create/{email}")
    public void registerNewCustomer(@PathVariable("email") String email) throws Exception {
        LocalDate ld = LocalDate.now();
        LocalDateTime d = LocalDateTime.now();
        Customer customer = new Customer(
            "Shivam","Amin",16,12,email,"14084314238",true,ld,"",d
        );
        customerService.addNewCustomer(customer);
    }

}
