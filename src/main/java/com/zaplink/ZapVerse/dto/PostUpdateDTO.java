package com.zaplink.ZapVerse.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Getter
public class PostUpdateDTO {

    private String topic;
    private String content;
    private Set<String> tags;
    private LocalDate modifiedAt;
    private int profileId;
}
