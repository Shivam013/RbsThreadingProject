package com.Rbs.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }


    public void addNewCustomer(Customer customer) throws Exception {
        Optional<Customer> customerByEmail = customerRepository.findCustomerByEmail(customer.getEmail());
        if(customerByEmail.isPresent()) throw new Exception("email taken");
        customerRepository.save(customer);
    }
}
