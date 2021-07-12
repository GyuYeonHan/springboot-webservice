package com.gyuyeon.springbook.domain.comments;

import com.gyuyeon.springbook.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    @Query("select c from Comments c where c.posts = ?1")
    List<Comments> findByPost(Posts post);

}
