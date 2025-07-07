package com.zaplink.ZapVerse.repository;

import com.zaplink.ZapVerse.model.React;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactRespository extends JpaRepository<React, Integer> {
}
