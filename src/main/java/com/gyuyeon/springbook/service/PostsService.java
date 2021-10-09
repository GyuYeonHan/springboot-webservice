package com.gyuyeon.springbook.service;

import com.gyuyeon.springbook.domain.Comments;
import com.gyuyeon.springbook.domain.post.ImageFile;
import com.gyuyeon.springbook.domain.post.Posts;
import com.gyuyeon.springbook.file.FileStore;
import com.gyuyeon.springbook.repository.CommentsRepository;
import com.gyuyeon.springbook.repository.PostsRepository;
import com.gyuyeon.springbook.repository.ImageRepository;
import com.gyuyeon.springbook.web.dto.PostsSaveRequestDto;
import com.gyuyeon.springbook.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;
    private final ImageRepository imageRepository;
    private final FileStore fileStore;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) throws IOException {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getCategory());

        return id;
    }

    public Posts findById(Long id) {
        return postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    }

    @Transactional(readOnly = true)
    public List<Posts> findAllDesc() {
        return postsRepository.findAllDesc();
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        List<Comments> commentsList = commentsRepository.findByPost(posts);
        List<ImageFile> uploadFileList = imageRepository.findByPost(posts);

        for (Comments comment : commentsList) {

            commentsRepository.delete(comment);
        }

        for (ImageFile uploadFile : uploadFileList) {
            fileStore.deleteFile(uploadFile.getStoreFileName());
            imageRepository.delete(uploadFile);
        }

        postsRepository.delete(posts);
    }
}
