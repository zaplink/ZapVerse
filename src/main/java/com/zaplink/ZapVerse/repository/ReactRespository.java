package com.zaplink.ZapVerse.repository;

import com.zaplink.ZapVerse.model.Post;
import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.model.React;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReactRespository extends JpaRepository<React, Integer> {
    Optional<React> findByPostAndProfile(Post post, Profile profile);
}
