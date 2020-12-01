package com.example.kafka.springbootwithkafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class TopicConfig 
{
	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

	@Value(value = "${kafka.topic-string}")
	private String topicName;

	@Value(value = "${kafka.topic-transaction}")
	private String transactionTopicName;

	@Bean
	public NewTopic generalTopic() {
		return TopicBuilder.name(topicName)
			      .partitions(1)
			      .replicas(1)
			      .build();
	}

	@Bean
	public NewTopic transactionTopic() {
		return TopicBuilder.name(transactionTopicName)
			      .partitions(1)
			      .replicas(1)
			      .build();
	}
	
	//If not using spring boot
	
	@Bean
    public KafkaAdmin kafkaAdmin() 
	{
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }
}