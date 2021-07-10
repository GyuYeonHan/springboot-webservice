package com.gyuyeon.springbook.service.comments;

import com.gyuyeon.springbook.domain.comments.Comments;
import com.gyuyeon.springbook.domain.comments.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;

    @Transactional
    public void save(Comments comments) {
        commentsRepository.save(comments);
    }
}
