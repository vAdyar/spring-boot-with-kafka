package com.example.kafka.springbootwithkafka.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {

    @KafkaListener(id="user", topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) throws Exception {
        if(message.startsWith("error"))
            throw new Exception("Testing DLT.");
        log.info(String.format("#### -> Consumed message -> %s", message));
    }

    @KafkaListener(id = "dltUser", topics = "${kafka.topic}.${kafka.dlt}")
    public void dltListen(String in) {
        log.info("Received from DLT: " + in);
    }
}