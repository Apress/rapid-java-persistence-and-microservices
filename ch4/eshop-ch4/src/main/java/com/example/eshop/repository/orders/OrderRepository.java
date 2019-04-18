package com.example.eshop.repository.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.eshop.model.orders.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{ }
