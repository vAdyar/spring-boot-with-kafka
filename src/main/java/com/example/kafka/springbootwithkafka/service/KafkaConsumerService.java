package com.example.kafka.springbootwithkafka.service;

import com.example.kafka.springbootwithkafka.DTO.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService
{
	private final Logger logger 
		= LoggerFactory.getLogger(KafkaConsumerService.class);
	
	@KafkaListener(topics = "${kafka.topic-string}",
			groupId = "${kafka.group-string}")
	public void consume(String message) {
		logger.info(String.format("Message recieved -> %s", message));
	}

	@KafkaListener(topics = "${kafka.topic-transaction}",
			groupId = "${kafka.group-transaction}",
			containerFactory = "transactionKafkaListenerContainerFactory")
	public void consume(Transaction transaction) {
		logger.info(String.format("Transaction received -> %s", transaction));
	}
}