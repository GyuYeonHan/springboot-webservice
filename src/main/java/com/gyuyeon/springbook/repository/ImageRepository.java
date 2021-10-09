package com.gyuyeon.springbook.repository;

import com.gyuyeon.springbook.domain.post.ImageFile;
import com.gyuyeon.springbook.domain.post.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageFile, Long> {

    @Query("select f from ImageFile f where f.posts = :post")
    List<ImageFile> findByPost(@Param("post") Posts posts);

    @Query("select f from ImageFile f where f.posts = :post order by f.storeFileName desc")
    List<ImageFile> findByPostDesc(@Param("post") Posts posts);
}
