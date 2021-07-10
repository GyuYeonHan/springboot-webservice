package com.gyuyeon.springbook.domain.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    @Query("SELECT c from Comments c ORDER BY c.id DESC")
    List<Comments> findAllDesc();

}
