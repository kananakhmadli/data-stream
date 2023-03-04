package com.company.messaging.consumer;

import com.company.messaging.Event;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AvroConsumer {

    @KafkaListener(topics = "${avro.topic.name}", containerFactory = "kafkaListenerContainerFactory",autoStartup = "false")
    public void read(ConsumerRecord<String, Event> record) {
        String key = record.key();
        Event event = record.value();
        System.out.println("Avro message received for key : " + key + " value : " + event.toString());
    }

}