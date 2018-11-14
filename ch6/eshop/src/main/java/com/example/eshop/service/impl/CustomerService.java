package com.example.eshop.service.impl;

import com.example.eshop.model.orders.Customer;
import com.example.eshop.repository.orders.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	public void registerNewCustomers() {
		Customer customer = new Customer();
		customer.setName("James Bond");
		customer.setEmail("james.bond@dummy.com");
		customer.setPassword("password");
		customer.setDateAdded(LocalDateTime.now());
		customerRepository.saveAndFlush(customer);
		log.info("All registered customers: " + customerRepository.findAll());
	}

	public List<Customer> findAll()	{
		return customerRepository.findAll();
	}

}
