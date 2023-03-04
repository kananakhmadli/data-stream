package com.company.messaging.events;

import lombok.Data;

@Data
public class AggregateDto {

    private String opType;
    private Long id;
    private String aggregateType;
    private String aggregateId;
    private String type;
    private String payload;

}