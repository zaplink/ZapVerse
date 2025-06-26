package com.zaplink.ZapVerse.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "profile")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String password;
    private String fname;
    private String lname;

    @OneToMany
    @JsonManagedReference
    private List<Post> posts;

    @OneToMany(mappedBy = "profile")
    @JsonManagedReference
    private List<FriendRequest> sentRequests;

    @OneToMany(mappedBy = "receiver")
    @JsonManagedReference
    private List<FriendRequest> receivedRequests;

    @OneToMany(mappedBy = "profile")
    @JsonManagedReference
//    @JsonIgnoreProperties("profile")
    private List<React> reacts;
}
