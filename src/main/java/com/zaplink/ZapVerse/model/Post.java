package com.zaplink.ZapVerse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "post")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String content;
    private String topic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Profile profile;

    @OneToMany
    private List<React> reacts;

}
