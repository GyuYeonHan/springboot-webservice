package com.gyuyeon.springbook.web.dto;

import com.gyuyeon.springbook.domain.post.Category;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PostsUpdateRequestDto {

    @NotNull
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String content;

    @NotNull
    private Category category;

    private List<MultipartFile> imageFiles;

    @Builder
    public PostsUpdateRequestDto(String title, String content, Category category, List<MultipartFile> imageFiles) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.imageFiles = imageFiles;
    }
}
