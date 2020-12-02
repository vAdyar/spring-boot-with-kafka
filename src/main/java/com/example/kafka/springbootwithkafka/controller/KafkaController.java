package com.example.kafka.springbootwithkafka.controller;

import com.example.kafka.springbootwithkafka.dto.Transaction;
import com.example.kafka.springbootwithkafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducerService service;

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.service.sendMessage(message);
    }

    @PostMapping("/publish/{transaction}")
    public void sendTransaction(@PathVariable("transaction") String transaction) {
        this.service.saveCreateTransactionLog(new Transaction(UUID.randomUUID(), transaction, Instant.now()));
    }
}