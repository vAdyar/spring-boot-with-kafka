package com.example.kafka.springbootwithkafka.service;

import com.example.kafka.springbootwithkafka.DTO.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducerService
{
	private static final Logger logger = 
			LoggerFactory.getLogger(KafkaProducerService.class);
	
	//1. General topic with string payload
	
	@Value(value = "${kafka.topic-string}")
    private String topicName;
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

	//2. Topic with transaction object payload
    
    @Value(value = "${kafka.topic-transaction}")
    private String transactionTopicName;
    
    @Autowired
    private KafkaTemplate<String, Transaction> transactionKafkaTemplate;
	
	public void sendMessage(String message) 
	{
		ListenableFuture<SendResult<String, String>> future 
			= this.kafkaTemplate.send(topicName, message);
		
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
            	logger.info("Sent message: " + message 
            			+ " with offset: " + result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
            	logger.error("Unable to send message : " + message, ex);
            }
       });
	}
	
	public void saveCreateTransactionLog(Transaction transaction) 
	{
		ListenableFuture<SendResult<String, Transaction>> future
			= this.transactionKafkaTemplate.send(transactionTopicName, transaction);
		
		future.addCallback(new ListenableFutureCallback<SendResult<String, Transaction>>() {
            @Override
            public void onSuccess(SendResult<String, Transaction> result) {
            	logger.info("Transaction created: " 
            			+ transaction + " with offset: " + result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
            	logger.error("Transaction created : " + transaction, ex);
            }
       });
	}
}