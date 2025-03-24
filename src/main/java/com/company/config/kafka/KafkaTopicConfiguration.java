//package com.company.config.kafka;
//
//import lombok.RequiredArgsConstructor;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.TopicBuilder;
//
//@Configuration
//@RequiredArgsConstructor
//public class KafkaTopicConfiguration {
//
//    @Value("${avro.topic.name}")
//    private String topicName;
//
//    @Bean
//    public NewTopic operation() {
//        return TopicBuilder.name(topicName)
//                .partitions(1)
//                .replicas(1)
//                .build();
//    }
//
//}