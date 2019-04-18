package com.example.eshop.repository.orders;

import com.example.eshop.model.orders.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }