package com.gyuyeon.springbook.domain.comments;

import com.gyuyeon.springbook.domain.BaseTimeEntity;
import com.gyuyeon.springbook.domain.posts.Posts;
import com.gyuyeon.springbook.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comments extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Posts posts;

    private String writer;

    private String content;

    @Builder
    public Comments(Posts posts, String writer, String content) {
        this.posts = posts;
        this.writer = writer;
        this.content = content;
    }
}
