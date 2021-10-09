package com.gyuyeon.springbook.web.dto;

import com.gyuyeon.springbook.domain.post.Category;
import com.gyuyeon.springbook.domain.post.Posts;
import com.gyuyeon.springbook.repository.ImageRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    private List<MultipartFile> imageFiles;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author, Category category, List<MultipartFile> imageFiles) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
        this.imageFiles = imageFiles;
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
