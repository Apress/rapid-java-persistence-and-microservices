package com.example.eshop.repository.orders;

import com.example.eshop.model.orders.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

    @Query("Select distinct c from Customer c left join fetch c.orders")
    List<Customer> findWithOrders();
}
