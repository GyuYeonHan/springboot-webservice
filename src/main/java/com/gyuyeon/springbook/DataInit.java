package com.gyuyeon.springbook;

import com.gyuyeon.springbook.domain.comments.Comments;
import com.gyuyeon.springbook.domain.comments.CommentsRepository;
import com.gyuyeon.springbook.domain.posts.Posts;
import com.gyuyeon.springbook.domain.posts.PostsRepository;
import com.gyuyeon.springbook.domain.user.Role;
import com.gyuyeon.springbook.domain.user.User;
import com.gyuyeon.springbook.domain.user.UserRepository;
import com.gyuyeon.springbook.service.comments.CommentsService;
import com.gyuyeon.springbook.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;

    private Posts posts;
    private User user;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        postInit();
        postsRepository.save(posts);

        userInit();
        userRepository.save(user);

        Comments comments1 = Comments.builder()
                .posts(posts)
                .writer("테스터")
                .content("테스트 댓글1")
                .build();

        Comments comments2 = Comments.builder()
                .posts(posts)
                .writer("테스터")
                .content("테스트 댓글2")
                .build();

        commentsRepository.save(comments1);
        commentsRepository.save(comments2);
    }

    private void postInit() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        posts = Posts.builder()
                .title(title)
                .content(content)
                .author("테스터")
                .build();

        postsRepository.save(posts);
    }
    private void userInit() {
        user = User.builder()
                .name("userA")
                .role(Role.GUEST)
                .email("test@email.com")
                .loginId("test")
                .password("test")
                .build();
    }

}
