package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

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

}
