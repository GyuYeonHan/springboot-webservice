package com.gyuyeon.springbook.domain;

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

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;

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
