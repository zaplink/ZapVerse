package com.zaplink.ZapVerse.dto;

import com.zaplink.ZapVerse.model.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TagDTO {
    private Set<String> tags;
}
