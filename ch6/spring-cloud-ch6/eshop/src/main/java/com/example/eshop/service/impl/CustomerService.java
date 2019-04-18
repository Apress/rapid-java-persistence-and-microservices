package com.example.eshop.service.impl;

import com.example.eshop.model.Customer;
import com.example.eshop.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	public void registerNewCustomers() {
		Customer customer = new Customer();
		customer.setName("Raj Malhotra");
		customer.setEmail("raj.malhotra@example.com");
		customer.setPassword("password");
		customerRepository.saveAndFlush(customer);
	}
}
