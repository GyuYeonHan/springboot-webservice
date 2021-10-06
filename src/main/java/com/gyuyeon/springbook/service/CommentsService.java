package com.gyuyeon.springbook.service;

import com.gyuyeon.springbook.domain.Comments;
import com.gyuyeon.springbook.repository.CommentsRepository;
import com.gyuyeon.springbook.domain.post.Posts;
import com.gyuyeon.springbook.web.dto.CommentsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;

    @Transactional
    public Long save(Comments comments) {
        return commentsRepository.save(comments).getId();
    }

    @Transactional
    public Long update(Long id, CommentsUpdateRequestDto requestDto) {
        Comments comment = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id="+ id));
        comment.update(requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Comments comment = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id="+ id));
        commentsRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    public List<Comments> findByPost(Posts post) {
        return commentsRepository.findByPost(post);
    }
}
