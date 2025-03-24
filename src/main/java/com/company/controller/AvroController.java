package com.company.controller;

import AKH.Event;
import com.company.messaging.events.DocumentDto;
import com.company.messaging.producer.AvroProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class AvroController {

    @Autowired
    private AvroProducer avroProducer;

    @PostMapping(value = "/produce")
    public void produce(@RequestBody DocumentDto documentDto) {
        Event event = Event.newBuilder()
                .setID(documentDto.getId())
                .setMESSAGE("Test")
                .build();
        avroProducer.send(event);
    }

}