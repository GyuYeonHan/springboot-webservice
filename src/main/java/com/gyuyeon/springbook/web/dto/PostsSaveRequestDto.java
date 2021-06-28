package com.gyuyeon.springbook.web.dto;

import com.gyuyeon.springbook.domain.posts.Posts;
import lombok.*;

import javax.validation.constraints.NotBlank;

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

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
