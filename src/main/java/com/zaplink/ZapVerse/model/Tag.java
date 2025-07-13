package com.zaplink.ZapVerse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tag")
@Getter
@Setter
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private TagType tagType;

    @ManyToOne
    @JsonBackReference
    private Post post;

    @ManyToOne
    @JsonBackReference
    private Profile profile;
}
