package com.zaplink.ZapVerse.dto;

import com.zaplink.ZapVerse.model.Profile;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class PostCreateDTO {

    private String topic;
    private String content;
    private Set<String> tags;
    private int profileId;

}
