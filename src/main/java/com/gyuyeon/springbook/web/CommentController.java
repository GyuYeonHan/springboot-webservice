package com.gyuyeon.springbook.web;

import com.gyuyeon.springbook.domain.Comments;
import com.gyuyeon.springbook.domain.post.Posts;
import com.gyuyeon.springbook.service.CommentsService;
import com.gyuyeon.springbook.service.PostsService;
import com.gyuyeon.springbook.web.dto.CommentsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class CommentController {

    private final PostsService postsService;
    private final CommentsService commentsService;

    @PostMapping("/{postId}/comment")
    public Long saveComment(@PathVariable Long postId,
                            @RequestBody CommentsSaveRequestDto dto) {
        Posts post = postsService.findById(postId);

        Comments comments = dto.toEntity();
        comments.setPosts(post);

        return commentsService.save(comments);
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentsService.delete(id);
    }
}
