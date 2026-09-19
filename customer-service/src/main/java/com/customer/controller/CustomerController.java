package com.customer.controller;

import com.customer.entity.CustomerEntity;
import com.customer.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/v1/api/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerEntity createCustomer(@RequestBody CustomerEntity body) {
        return customerRepository.save(body);
    }

    @GetMapping("/v1/api/customers")
    public List<CustomerEntity> listCustomers() {
        return customerRepository.findAll();
    }
}