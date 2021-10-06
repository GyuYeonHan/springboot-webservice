package com.gyuyeon.springbook.web.dto;

import com.gyuyeon.springbook.domain.post.Category;
import com.gyuyeon.springbook.domain.post.Posts;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PostsSaveRequestDto {

    public PostsSaveRequestDto() {
    }

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String author;

    @NotNull
    private Category category;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author, Category category) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .category(category)
                .build();
    }
}
