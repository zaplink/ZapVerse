package com.zaplink.ZapVerse.repository;

import com.zaplink.ZapVerse.model.Post;
import com.zaplink.ZapVerse.model.TagType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findDistinctByTags_TagType(TagType tagType);
}
