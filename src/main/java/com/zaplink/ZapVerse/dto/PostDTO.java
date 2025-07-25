package com.zaplink.ZapVerse.dto;

import com.zaplink.ZapVerse.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private int id;
    private String topic;
    private String content;
    private Set<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String fname;
    private String lname;
    private String avatar;
    private long loveCount;
    private boolean userReacted;
}

