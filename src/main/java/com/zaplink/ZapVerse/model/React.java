package com.zaplink.ZapVerse.model;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;



}
