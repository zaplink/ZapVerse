package com.zaplink.ZapVerse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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

    @Column(unique = true)
    private String email;

    private String password;
    private String fname;
    private String lname;

    @JsonIgnore
    private String avatar;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @OneToMany
    private List<FriendRequest> sentRequests;

    @OneToMany
    private List<FriendRequest> receivedRequests;

    @OneToMany
    private Set<Tag> tags;

    @ManyToMany
    @JoinTable(name = "friends", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<Profile> friends = new HashSet<>();
}
