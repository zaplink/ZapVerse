package com.zaplink.ZapVerse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity(name = "post")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post {

    public Post(String topic, String content, Profile profile, List<React> reactions) {
        this.topic = topic;
        this.content = content;
        this.profile = profile;
        this.reactions = reactions;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String topic;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
//    @JsonBackReference
    @JsonIgnoreProperties("posts")
    private Profile profile;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    private List<React> reactions;

    @OneToMany
    private Set<Tag> tags;
}
