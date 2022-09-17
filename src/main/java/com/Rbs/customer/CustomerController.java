package com.Rbs.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
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
        Customer customer = new Customer("STA",
                email,
                LocalDate.of(2004, Month.JANUARY, 16),
                "14084314238");
        customerService.addNewCustomer(customer);
    }

}
