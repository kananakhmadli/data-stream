package com.company.config.kafka;

import AKH.Event;
import org.apache.kafka.common.security.plain.PlainLoginModule;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.Map;

@Configuration
public class KafkaConfiguration {

    private static final String JAAS_CONFIG_PRODUCER =
            String.format("%s required username=\"%s\" " + "password=\"%s\";",
                    PlainLoginModule.class.getName(),
                    "atlas_admin",
                    "Pre@!lasadmin07112023");

    @Bean
    public ConsumerFactory<String, Event> consumerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> stringObjectMap = kafkaProperties.buildConsumerProperties();
//        stringObjectMap.put(SaslConfigs.SASL_JAAS_CONFIG, JAAS_CONFIG_PRODUCER);
//        stringObjectMap.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
//        stringObjectMap.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        return new DefaultKafkaConsumerFactory<>(stringObjectMap);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Event>> kafkaListenerContainerFactory(KafkaProperties kafkaProperties) {
        ConcurrentKafkaListenerContainerFactory<String, Event> factory = new ConcurrentKafkaListenerContainerFactory<String, Event>();
        factory.setConsumerFactory(consumerFactory(kafkaProperties));
        return factory;
    }


    @Bean
    public KafkaTemplate<String, Event> kafkaTemplate(KafkaProperties kafkaProperties) {
        return new KafkaTemplate<>(kafkaProducerFactory(kafkaProperties));
    }

    @Bean
    public ProducerFactory<String, Event> kafkaProducerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> stringObjectMap = kafkaProperties.buildProducerProperties();
//        stringObjectMap.put(SaslConfigs.SASL_JAAS_CONFIG, JAAS_CONFIG_PRODUCER);
//        stringObjectMap.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
//        stringObjectMap.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        return new DefaultKafkaProducerFactory<>(stringObjectMap);
    }

}