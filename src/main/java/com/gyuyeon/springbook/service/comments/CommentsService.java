package com.gyuyeon.springbook.service.comments;

import com.gyuyeon.springbook.domain.comments.Comments;
import com.gyuyeon.springbook.domain.comments.CommentsRepository;
import com.gyuyeon.springbook.domain.posts.Posts;
import com.gyuyeon.springbook.web.dto.PostsListResponseDto;
import com.gyuyeon.springbook.web.dto.PostsResponseDto;
import com.gyuyeon.springbook.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;

    @Transactional
    public void save(Comments comments) {
        commentsRepository.save(comments);
    }

    @Transactional
    public void delete(Comments comments) {
        commentsRepository.delete(comments);
    }

    @Transactional(readOnly = true)
    public List<Comments> findByPost(Posts post) {
        List<Comments> commentsOfPost = commentsRepository.findByPost(post);

        return commentsOfPost;
    }
}
