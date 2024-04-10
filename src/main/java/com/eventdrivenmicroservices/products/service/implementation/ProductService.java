package com.eventdrivenmicroservices.products.service.implementation;

import com.eventdrivenmicroservices.products.model.Product;
import com.eventdrivenmicroservices.products.service.definition.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductService implements IProductService {
    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    public ProductService(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(Product product) throws Exception{
        String productId = UUID.randomUUID().toString();

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId, product.getName(), product.getPrice(), product.getQuantity());
        // Publish the event to Kafka broker asynchronously [do not block the main thread]
//        CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate.send("product-created", productId, productCreatedEvent);
//        future.whenComplete((result, exception) -> {
//            if (exception == null) {
//                LOGGER.info("Product created event published successfully{}", result.getRecordMetadata());
//            } else {
//                LOGGER.error("Error occurred while publishing product created event{}", exception.getMessage());
//            }
//        });

        LOGGER.info("before sending product created event to kafka broker");

      //publish the event to Kafka broker synchronously [block the main thread]
        SendResult<String, ProductCreatedEvent> result = kafkaTemplate.send("product-created-event-topic", productId, productCreatedEvent).get();

        LOGGER.info("Partition: {}", result.getRecordMetadata().partition());
        LOGGER.info("Offset: {}", result.getRecordMetadata().offset());
        LOGGER.info("Timestamp: {}", new Date(result.getRecordMetadata().timestamp()));
        LOGGER.info("Topic: {}", result.getRecordMetadata().topic());

        LOGGER.info("Returning product id: {}", productId);

        return productId;
    }
}
