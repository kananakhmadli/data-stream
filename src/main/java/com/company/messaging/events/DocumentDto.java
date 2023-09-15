package com.company.messaging.events;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DocumentDto {

    private Integer branch;
    private Integer type;
    private Long docNo;
    private Long oldDocNo;
    private Long newDocNo;
    private LocalDateTime opDate;

}