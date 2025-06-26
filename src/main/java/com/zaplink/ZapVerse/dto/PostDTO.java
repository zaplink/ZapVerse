package com.zaplink.ZapVerse.dto;

import com.zaplink.ZapVerse.model.Tag;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
public class PostDTO {

    private int id;
    private String topic;
    private String content;
    private Set<String> tags;
    private LocalDate createdAt;
    private LocalDate modifiedAt;
}
