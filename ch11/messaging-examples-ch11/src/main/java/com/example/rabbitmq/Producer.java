package com.example.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@Slf4j
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRate = 1000)
    public void send() {
        String message = "Next order received at " + Calendar.getInstance().getTime();
        this.rabbitTemplate.convertAndSend("orders_exchange", "orders", message);
        log.info("RabbitMQ Message sent : {}", message);
    }
}
