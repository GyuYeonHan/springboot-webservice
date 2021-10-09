package com.gyuyeon.springbook.domain.post;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column(nullable = false)
    private String storeFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Posts posts;

    @Builder
    public ImageFile(String storeFileName) {
        this.storeFileName = storeFileName;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
        posts.getImageFiles().add(this);
    }
}
