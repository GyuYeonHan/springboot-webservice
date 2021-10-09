package com.gyuyeon.springbook.web.dto;

import com.gyuyeon.springbook.domain.post.Category;
import com.gyuyeon.springbook.domain.post.ImageFile;
import com.gyuyeon.springbook.domain.post.Posts;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private Category category;
    private List<String> imageFiles;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.category = entity.getCategory();
        this.imageFiles = entity.getImageFiles().stream().map(ImageFile::getStoreFileName).collect(Collectors.toList());
    }
}
