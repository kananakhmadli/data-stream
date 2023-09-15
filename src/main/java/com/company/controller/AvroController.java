package com.company.controller;

import com.company.messaging.events.Document;
import com.company.messaging.events.DocumentDto;
import com.company.messaging.producer.AvroProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/kafka")
public class AvroController {

    @Autowired
    private AvroProducer avroProducer;

    @PostMapping(value = "/produce")
    public void produce(@RequestBody DocumentDto documentDto) {
        String formatter = documentDto.getOpDate().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Document event = Document.newBuilder()
                .setDOCNO(documentDto.getDocNo())
                .setNEWDOCNO(documentDto.getNewDocNo())
                .setOLDDOCNO(documentDto.getOldDocNo())
                .setBRANCH(documentDto.getBranch())
                .setDOCTYPE(documentDto.getType())
                .setOPDATE(formatter)
                .build();
        avroProducer.send(event);
    }

}