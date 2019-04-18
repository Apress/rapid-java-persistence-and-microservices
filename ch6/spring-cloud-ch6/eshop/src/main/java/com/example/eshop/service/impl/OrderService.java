package com.example.eshop.service.impl;

import com.example.eshop.model.Order;
import com.example.eshop.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    @LoadBalanced
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "handleInventoryFailure",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2")
            })
    public Order orderProduct()   {

        Order order = null;

        Map<String, Integer> map = null;
        ObjectMapper mapper = new ObjectMapper();
        String resultJson = this.restTemplate
                .getForObject("http://INVENTORY-SERVICE/inventory/api/inventory/" + 1, String.class);

        try {
            map = mapper.readValue(resultJson.getBytes(), HashMap.class);
            log.info("Result from inventory service: {}", map);
        }catch(Exception e) {
            log.error("Error while reading back json value: {}", map);
            return null;
        }

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
        log.error("Inventory Service is responding very slow");
        return null;
    }

}
