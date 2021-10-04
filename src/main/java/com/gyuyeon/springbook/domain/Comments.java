package com.gyuyeon.springbook.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comments extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Posts posts;

    @Builder
    public Comments(Posts posts, String writer, String content) {
        this.posts = posts;
        this.writer = writer;
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }
}
