package com.zaplink.ZapVerse.utility;

import com.zaplink.ZapVerse.dto.TagDTO;
import com.zaplink.ZapVerse.model.Tag;

import java.util.HashSet;
import java.util.Set;

public class TagMapper {
    public static Set<String> toDTO(Set<Tag> tags) {
        Set<String> tagStrings = new HashSet<>();
        for (Tag tag : tags) {
            tagStrings.add(tag.getTagType().toString().toLowerCase());
        }
        return tagStrings;
    }
}
