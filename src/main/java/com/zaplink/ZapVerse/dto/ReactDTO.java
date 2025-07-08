package com.zaplink.ZapVerse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReactDTO {

    private int postId;
    private int profileId;
    private String reaction; // true = heart clicked, false = heart unclicked
}
