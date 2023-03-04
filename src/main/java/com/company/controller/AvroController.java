package com.company.controller;

import com.company.messaging.events.AggregateDto;
import com.company.messaging.Event;
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
    public void produce(@RequestBody AggregateDto aggregateDto) {
        Event event = Event.newBuilder()
                .setOpType(aggregateDto.getOpType())
                .setID(aggregateDto.getId())
                .setAGGREGATEID(aggregateDto.getAggregateId())
                .setPAYLOAD(aggregateDto.getPayload())
                .setTYPE(aggregateDto.getType())
                .setAGGREGATETYPE(aggregateDto.getAggregateType())
                .build();
        avroProducer.send(event);
    }

}