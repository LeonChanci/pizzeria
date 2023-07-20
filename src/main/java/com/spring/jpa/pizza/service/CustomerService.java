package com.spring.jpa.pizza.service;

import com.spring.jpa.pizza.persitence.entity.CustomerEntity;
import com.spring.jpa.pizza.persitence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity findByPhone(String phone){
        return customerRepository.finBnByPhone(phone);
    }
}
