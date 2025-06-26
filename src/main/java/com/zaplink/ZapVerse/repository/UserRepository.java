package com.zaplink.ZapVerse.repository;

import com.zaplink.ZapVerse.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Profile, Integer> {
}
