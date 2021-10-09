package com.gyuyeon.springbook.service;

import com.gyuyeon.springbook.domain.post.ImageFile;
import com.gyuyeon.springbook.domain.post.Posts;
import com.gyuyeon.springbook.file.FileStore;
import com.gyuyeon.springbook.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final FileStore fileStore;

    @Transactional
    public List<ImageFile> saveImagesToPost(List<MultipartFile> imageFiles, Posts posts) throws IOException {
        return fileStore.storeFiles(imageFiles, posts);
    }

    @Transactional(readOnly = true)
    public List<ImageFile> findByPostDesc(Posts posts) {
        return imageRepository.findByPostDesc(posts);
    }
}
