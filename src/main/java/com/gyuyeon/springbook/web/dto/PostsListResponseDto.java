package com.gyuyeon.springbook.web.dto;

import com.gyuyeon.springbook.domain.post.Category;
import com.gyuyeon.springbook.domain.post.Posts;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class PostsListResponseDto {

    private Long id;
    private Category category;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;
    private boolean isNew;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.category = entity.getCategory();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
        this.isNew = modifiedDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")).equals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }
}
