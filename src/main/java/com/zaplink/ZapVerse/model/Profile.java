package com.zaplink.ZapVerse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String email;
    private String password;
    private String fname;
    private String lname;

    @OneToMany
    @JsonIgnore
    private List<Post> posts;

    @OneToMany
    private List<FriendRequest> sentRequests;

    @OneToMany
    private List<FriendRequest> receivedRequests;

    // Constructor Without : posts, sentRequests, receivedRequests
    public Profile(String email, String password, String fname, String lname) {
        this.email = email;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
    }
}
