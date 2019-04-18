package com.example.eshop;

import com.example.eshop.model.dto.CustomerDto;
import com.example.eshop.model.orders.Customer;
import com.example.eshop.model.projection.CustomerProjection;
import com.example.eshop.repository.history.PurchaseHistoryRepository;
import com.example.eshop.repository.orders.CustomerRepository;
import com.example.eshop.repository.orders.OrderRepository;
import com.example.eshop.service.impl.CustomerService;
import com.example.eshop.service.impl.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

	@Autowired
	ProductService productService;

	@Autowired
	CustomerService customerService;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	PurchaseHistoryRepository purchaseHistoryRepository;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		customerService.registerNewCustomers();
		productService.registerNewProducts();
		productService.purchase(1l, 1l, 2, 400);
		productService.saveHistory(1l, 1l);

		log.info("Customers {}", customerService.findAll());
		log.info("Products {}", productService.findAll());
		log.info("Orders {}", orderRepository.findAll());
		log.info("PurchaseHistory {}", purchaseHistoryRepository.findAll());

		nPlusOneExample();
		constructorMappingExample();
		projectionsExample();
	}

	public void nPlusOneExample()	{
		List<Customer> customerList = customerRepository.findCustomersWithOrderDetails();
		log.info("Customers List with Order Details: {}", customerList);
	}

	public void constructorMappingExample()	{
		List<CustomerDto> customersList = customerRepository.findCustomers();
		log.info("Customers List with Constructors mapped query results: {}", customersList);
	}

	public void projectionsExample()	{
		List<CustomerProjection> customers = customerRepository.findAllCustomers();
		log.info("Customers List with Projections");
		customers.forEach(customer -> {
			log.info("Projections Example, Customer Name With Email: {}", customer.getCustomerNameWithEmail());
		});

		log.info("Projections Example, Find Single Customer : {}",
				customerRepository.findOneByName("Raj Malhotra").getCustomerNameWithEmail());
	}

}
