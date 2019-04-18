package com.example.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {

    @RabbitListener(queues = "orders")
    public void receiveMessage(String message) {
        log.info("RabbitMQ Received :: {}", message);
    }
}
