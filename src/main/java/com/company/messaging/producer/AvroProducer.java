package com.company.messaging.producer;

import AKH.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class AvroProducer {

    @Value("${avro.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Event> kafkaTemplate;

    public void send(Event event) {
        var future = kafkaTemplate.send(topicName, String.valueOf(event.getID()), event);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, Event> result) {
                System.out.println("Avro message successfully produced");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Message failed to produce");
            }
        });
    }

}