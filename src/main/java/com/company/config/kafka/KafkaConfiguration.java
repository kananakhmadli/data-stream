package com.company.config.kafka;

import KB.TLG;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.security.plain.PlainLoginModule;
import org.apache.kafka.common.serialization.StringDeserializer;
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
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    private static final String JAAS_CONFIG_PRODUCER =
            String.format("%s required username=\"%s\" " + "password=\"%s\";",
                    PlainLoginModule.class.getName(),
                    "atlas_admin",
                    "Pre@!lasadmin07112023");

    @Bean
    public ConsumerFactory<String, TLG> consumerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> stringObjectMap = kafkaProperties.buildConsumerProperties();
        stringObjectMap.put(SaslConfigs.SASL_JAAS_CONFIG, JAAS_CONFIG_PRODUCER);
        stringObjectMap.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        stringObjectMap.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        return new DefaultKafkaConsumerFactory<>(stringObjectMap);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, TLG>> kafkaListenerContainerFactory(KafkaProperties kafkaProperties) {
        ConcurrentKafkaListenerContainerFactory<String, TLG> factory = new ConcurrentKafkaListenerContainerFactory<String, TLG>();
        factory.setConsumerFactory(consumerFactory(kafkaProperties));
        return factory;
    }


    @Bean
    public KafkaTemplate<String, TLG> kafkaTemplate(KafkaProperties kafkaProperties) {
        return new KafkaTemplate<>(kafkaProducerFactory(kafkaProperties));
    }

    @Bean
    public ProducerFactory<String, TLG> kafkaProducerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> stringObjectMap = kafkaProperties.buildProducerProperties();
        stringObjectMap.put(SaslConfigs.SASL_JAAS_CONFIG, JAAS_CONFIG_PRODUCER);
        stringObjectMap.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        stringObjectMap.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        return new DefaultKafkaProducerFactory<>(stringObjectMap);
    }

}