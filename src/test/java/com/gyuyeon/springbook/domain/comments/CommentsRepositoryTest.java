package com.gyuyeon.springbook.domain.comments;

import com.gyuyeon.springbook.domain.posts.Posts;
import com.gyuyeon.springbook.domain.posts.PostsRepository;
import com.gyuyeon.springbook.domain.user.Role;
import com.gyuyeon.springbook.domain.user.User;
import com.gyuyeon.springbook.domain.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CommentsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    UserRepository userRepository;

    Posts posts;
    User user;

    @BeforeEach
    void setUp() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        posts = Posts.builder()
                .title(title)
                .content(content)
                .author("gks951020@gmail.com")
                .build();

        postsRepository.save(posts);

        user = User.builder()
                .name("userA")
                .role(Role.GUEST)
                .email("test@email.com")
                .loginId("testid")
                .password("testpassword")
                .build();

        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        commentsRepository.deleteAll();
    }

    @Test
    @DisplayName("Post와 Comment 연결 확인")
    void test1() {
        //given
        Comments comments = Comments.builder()
                .posts(posts)
                .user(user)
                .content("테스트 댓글")
                .build();

        //when
        Comments savedComment = commentsRepository.save(comments);

        //then
        Assertions.assertThat(savedComment.getPosts()).isEqualTo(posts);
    }

    @Test
    @DisplayName("댓글 생성 성공")
    void test2() {
        //given
        Comments comments = Comments.builder()
                .posts(posts)
                .user(user)
                .content("테스트 댓글")
                .build();

        //when
        Comments savedComment = commentsRepository.save(comments);

        //then
        Assertions.assertThat(savedComment).isEqualTo(comments);
    }

    @Test
    @DisplayName("댓글 수정 성공")
    void test3() {
        //given
        Comments comments = Comments.builder()
                .posts(posts)
                .user(user)
                .content("테스트 댓글")
                .build();

        Comments savedComment = commentsRepository.save(comments);

        //when
        String content = "수정된 댓글";
        comments.setContent(content);

        //then
        Assertions.assertThat(savedComment.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("댓글 삭제 성공")
    void test4() {
        //given
        Comments comments = Comments.builder()
                .posts(posts)
                .user(user)
                .content("테스트 댓글")
                .build();

        Comments savedComment = commentsRepository.save(comments);
        Long id = savedComment.getId();

        //when
        commentsRepository.delete(comments);
        Comments deletedComments = commentsRepository.findById(id).orElse(null);

        //then
        Assertions.assertThat(deletedComments).isNull();
    }
}
