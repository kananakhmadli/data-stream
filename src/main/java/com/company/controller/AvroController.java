package com.company.controller;

import KB.CUSTOMER;
import KB.TLG;
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

//        String formatter = documentDto.getOpDate().format(
//                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        TLG event = TLG.newBuilder()
                .setID(documentDto.getId())
                .setPROFILEID(documentDto.getProfileId())
                .setPROFILETITLE(documentDto.getProfileTitle())
                .setTRANNUMBER(documentDto.getTransactionNumber())
                .setTYPE(documentDto.getType())
                .setTIME(documentDto.getTime())
                .setORIGTIME(documentDto.getOrigTime())
                .setTERMNAME(documentDto.getTermName())
                .setTERMSIC(documentDto.getTermSic())
                .setTERMCOUNTRY(documentDto.getTermCountry())
                .setTERMLOCATION(documentDto.getTermLocation())
                .setTRANCODE(documentDto.getTranCode())
                .setTERMOWNER(documentDto.getTermOwner())
                .setTERMRETAILERNAME(documentDto.getTermRetailerName())
                .setPOSCONDITION(documentDto.getPosCondition())
                .setEXCHANGERATEACCT(documentDto.getExchangeRateAcct())
                .setAMOUNT(documentDto.getAmount())
                .setPAN(documentDto.getPan())
                .setVIRTUALPAN(documentDto.getVirtualPan())
                .setTIN(documentDto.getTin())
                .setCUSTOMER(CUSTOMER.newBuilder()
                        .setPIN(documentDto.getCustomer().getPin())
                        .setPHONENUMBER(documentDto.getCustomer().getPhoneNumber())
                        .setUMICOID(documentDto.getCustomer().getUmicoid())
                        .build())
                .build();
        System.out.println(event);
        avroProducer.send(event);
    }

}