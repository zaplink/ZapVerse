package com.zaplink.ZapVerse.repository;

import com.zaplink.ZapVerse.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRespository extends JpaRepository<Like, Integer> {
}
