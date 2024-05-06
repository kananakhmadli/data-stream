package com.company.messaging.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Customer {

    @JsonProperty("PIN")
    private String pin;

    @JsonProperty("PHONENUMBER")
    private String phoneNumber;

    @JsonProperty("UMICOID")
    private String umicoid;

}