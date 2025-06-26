package com.zaplink.ZapVerse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity(name = "profile")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String email;
    private String password;
    private String fname;
    private String lname;

    @OneToMany
    private List<Post> posts;

    @OneToMany
    private List<FriendRequest> sentRequests;

    @OneToMany
    private List<FriendRequest> receivedRequests;

    @OneToMany
    private Set<Tag> tags;
}
