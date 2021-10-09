package com.gyuyeon.springbook;

import com.gyuyeon.springbook.domain.Comments;
import com.gyuyeon.springbook.domain.post.Category;
import com.gyuyeon.springbook.domain.post.ImageFile;
import com.gyuyeon.springbook.repository.CommentsRepository;
import com.gyuyeon.springbook.domain.post.Posts;
import com.gyuyeon.springbook.repository.ImageRepository;
import com.gyuyeon.springbook.repository.PostsRepository;
import com.gyuyeon.springbook.domain.user.Role;
import com.gyuyeon.springbook.domain.user.User;
import com.gyuyeon.springbook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Profile("local")
@Component
@RequiredArgsConstructor
public class DataInit {

    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    private Posts posts;
    private User user;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        userInit();
        postInit();
        commentInit();
        imageInit();
    }

    private void imageInit() {
        ImageFile image = ImageFile.builder()
                .storeFileName("05d2846e-0e02-4aa0-acb9-dc2eeb591a87.png")
                .build();

        image.setPosts(posts);
        imageRepository.save(image);
    }

    private void commentInit() {
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
                .category(Category.일기)
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

        userRepository.save(user);
    }

}
