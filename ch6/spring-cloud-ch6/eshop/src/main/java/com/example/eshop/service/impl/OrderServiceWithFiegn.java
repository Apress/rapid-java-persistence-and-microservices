package com.example.eshop.service.impl;

import com.example.eshop.http.InventoryClient;
import com.example.eshop.model.Order;
import com.example.eshop.repository.OrderRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
public class OrderServiceWithFiegn {

        @Autowired
        OrderRepository orderRepository;

        @Autowired
        InventoryClient inventoryClient;

        @HystrixCommand(fallbackMethod = "handleInventoryFailure")
        public Order orderProduct()   {

            Order order = null;

            Map<String, Integer> map = inventoryClient.getInventory(1l);
            log.info("Result from inventory service through feign: {}", map);

            Integer qty = map.get("quantity");
            if(qty >=2)
                order = createOrder(1l, 1l, 2, 400);

            log.info("Orders {}", orderRepository.findAll());

            return order;
        }

        public Order createOrder(Long productId, Long customerId, int quantity, int price) {
            Order order = new Order();
            order.setCustomerId(customerId);
            order.setProductId(productId);
            order.setPrice(price);
            order.setQuantity(quantity);
            order = orderRepository.save(order);
            return order;
        }

        private Order handleInventoryFailure() {
            log.error("Cannot connect to inventory service with 20% requests failing in 10 seconds interval");
            return null;
        }

    }
