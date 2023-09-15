package com.company.config.kafka;

import com.company.messaging.events.Document;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
public class KafkaConfiguration {

    @Bean
    public ConsumerFactory<String, Document> consumerFactory(KafkaProperties kafkaProperties) {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Document>> kafkaListenerContainerFactory(KafkaProperties kafkaProperties) {
        ConcurrentKafkaListenerContainerFactory<String, Document> factory = new ConcurrentKafkaListenerContainerFactory<String, Document>();
        factory.setConsumerFactory(consumerFactory(kafkaProperties));
        return factory;
    }

}