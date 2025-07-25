package com.zaplink.ZapVerse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "friend_request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    @JsonBackReference
    private Profile receiver;
}

