package com.gyuyeon.springbook.service.comments;

import com.gyuyeon.springbook.domain.Comments;
import com.gyuyeon.springbook.repository.CommentsRepository;
import com.gyuyeon.springbook.domain.Posts;
import com.gyuyeon.springbook.repository.PostsRepository;
import com.gyuyeon.springbook.service.CommentsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommentsServiceTest {

    @Autowired
    PostsRepository postsRepository;

    @Autowired CommentsRepository commentsRepository;

    @Autowired
    CommentsService commentsService;

    Posts posts;

    @BeforeEach
    void setUp() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        posts = Posts.builder()
                .title(title)
                .content(content)
                .author("test@gmail.com")
                .build();

        postsRepository.save(posts);
    }

    @AfterEach
    void tearDown() {
        commentsRepository.deleteAll();
    }

    @Test
    @DisplayName("해당 게시글의 댓글이 모두 조회된다.")
    void test1() {
        //given
        Comments comment1 = Comments.builder()
                .posts(posts)
                .writer("테스터")
                .content("테스트 댓글1")
                .build();

        Comments comment2 = Comments.builder()
                .posts(posts)
                .writer("테스터")
                .content("테스트 댓글2")
                .build();

        Comments comment3 = Comments.builder()
                .posts(posts)
                .writer("테스터")
                .content("테스트 댓글3")
                .build();

        commentsRepository.save(comment1);
        commentsRepository.save(comment2);
        commentsRepository.save(comment3);

        //when
        List<Comments> commentsList = commentsService.findByPost(posts);

        //then
        Assertions.assertThat(commentsList).hasSize(3);
        Assertions.assertThat(commentsList.get(0).getContent()).isEqualTo("테스트 댓글1");
        Assertions.assertThat(commentsList.get(1).getContent()).isEqualTo("테스트 댓글2");
        Assertions.assertThat(commentsList.get(2).getContent()).isEqualTo("테스트 댓글3");
    }
}