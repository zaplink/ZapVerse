package com.zaplink.ZapVerse.dto;

import com.zaplink.ZapVerse.model.Profile;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Getter
public class PostCreateDTO {

    private String topic;
    private String content;
    private Set<String> tags;
    private LocalDate createdAt;
    private int profileId;
}
