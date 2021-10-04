package com.gyuyeon.springbook.repository;

import com.gyuyeon.springbook.domain.Comments;
import com.gyuyeon.springbook.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    @Query("select c from Comments c where c.posts = :post")
    List<Comments> findByPost(@Param("post") Posts posts);

}
