package com.example.kafka.springbootwithkafka.DTO;


import lombok.Data;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Data
@ToString
public class Transaction {

    public Transaction() {
    }

    public Transaction(UUID id, String type, Instant executedTime) {
        this.id = id;
        this.type = type;
        this.executedTime = executedTime;
    }

    private UUID id;
    private String type;
    private Instant executedTime;

}
