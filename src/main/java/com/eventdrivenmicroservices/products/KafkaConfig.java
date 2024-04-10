package com.eventdrivenmicroservices.products;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Configuration
public class KafkaConfig {
    // Add Kafka configuration here
    @Bean
    NewTopic createTopic() {
        return TopicBuilder.name("product-created-event-topic")
                .partitions(3)
                .replicas(1)
                .configs(Map.of("min.insync.replicas", "1"))
                .build();
    }
}
