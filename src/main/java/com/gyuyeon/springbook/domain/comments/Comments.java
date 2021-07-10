package com.gyuyeon.springbook.domain.comments;

import com.gyuyeon.springbook.domain.BaseTimeEntity;
import com.gyuyeon.springbook.domain.posts.Posts;
import com.gyuyeon.springbook.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    @Builder
    public Comments(Posts posts, User user, String content) {
        this.posts = posts;
        this.user = user;
        this.content = content;
    }
}
