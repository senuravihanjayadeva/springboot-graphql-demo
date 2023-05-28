package com.senura.hplus.controller;

import com.senura.hplus.data.Customer;
import com.senura.hplus.data.CustomerRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @QueryMapping
    public Iterable<Customer> customers(){
        return this.customerRepository.findAll();
    }
    @QueryMapping
    public Customer customerById(@Argument Long id){
        return this.customerRepository.findById(id).orElseThrow();
    }
    @QueryMapping
    public Customer customerByEmail(@Argument String email){
        return this.customerRepository.findCustomerByEmail(email);
    }
    @MutationMapping
    public Customer addCustomer(@Argument(name = "input") CustomerInput customerInput){
        return this.customerRepository.save(customerInput.getCustomerEntity());
    }
}
