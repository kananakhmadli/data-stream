package com.company.messaging.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DocumentDto {

    @JsonProperty("ID")
    private long id;

    @JsonProperty("PROFILEID")
    private int profileId;

    @JsonProperty("PROFILETITLE")
    private String profileTitle;

    @JsonProperty("TRANNUMBER")
    private String transactionNumber;

    @JsonProperty("TYPE")
    private int type;

    @JsonProperty("TIME")
    private String time;

    @JsonProperty("ORIGTIME")
    private String origTime;

    @JsonProperty("TERMNAME")
    private String termName;

    @JsonProperty("TERMSIC")
    private int termSic;

    @JsonProperty("TERMCOUNTRY")
    private int termCountry;

    @JsonProperty("TERMLOCATION")
    private String termLocation;

    @JsonProperty("TRANCODE")
    private int tranCode;

    @JsonProperty("TERMOWNER")
    private String termOwner;

    @JsonProperty("TERMRETAILERNAME")
    private String termRetailerName;

    @JsonProperty("POSCONDITION")
    private Long posCondition;

    @JsonProperty("EXCHANGERATEACCT")
    private double exchangeRateAcct;

    @JsonProperty("AMOUNT")
    private Double amount;

    @JsonProperty("PAN")
    private String pan;

    @JsonProperty("VIRTUALPAN")
    private String virtualPan;

    @JsonProperty("TIN")
    private String tin;

    @JsonProperty("CUSTOMER")
    private Customer customer;

}