package com.zaplink.ZapVerse.dto;

import com.zaplink.ZapVerse.model.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReactDTO {

    private int postId;
    private int profileId;
    private ReactionType reaction;


}
