package com.example.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "orders", groupId = "order_grp")
    public void consume(String message) throws IOException {
        log.info("Kafka Received :: {}", message);
    }
}
