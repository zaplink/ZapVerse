package com.zaplink.ZapVerse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "react")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class React {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonBackReference
    private Post post;

    @ManyToOne
    @JsonBackReference
    private Profile profile;

    private String reaction;
}
