package com.gyuyeon.springbook.web.dto;

import com.gyuyeon.springbook.domain.Comments;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentsSaveRequestDto {

    public CommentsSaveRequestDto() {
    }

    @NotBlank
    private String content;

    @NotBlank
    private String writer;

    @Builder
    public CommentsSaveRequestDto(String content, String writer) {
        this.content = content;
        this.writer = writer;
    }

    public Comments toEntity() {
        return Comments.builder()
                .content(content)
                .writer(writer)
                .build();
    }
}
