package com.gyuyeon.springbook.repository;

import com.gyuyeon.springbook.domain.post.Category;
import com.gyuyeon.springbook.domain.post.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("select p from Posts p order by p.id desc")
    List<Posts> findAllDesc();

    @Query("select p from Posts p join fetch p.commentsList order by p.id desc")
    Optional<Posts> findFetchById(Long id);

    @Query("select p from Posts p where p.category = :category order by p.id desc")
    List<Posts> findByCategory(Category category);
}
