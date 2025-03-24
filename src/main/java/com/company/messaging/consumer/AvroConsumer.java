package com.company.messaging.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AvroConsumer {

    @KafkaListener(topics = "cms.A4M.TEXTRACTDELAY")
    public void read(ConsumerRecord<String, String> record) {
//        org.apache.kafka.common.serialization
        System.out.println("Avro message received for value : " + record.value());
    }

}