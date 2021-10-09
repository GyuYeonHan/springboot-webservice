package com.gyuyeon.springbook.service;

import com.gyuyeon.springbook.domain.post.ImageFile;
import com.gyuyeon.springbook.domain.post.Posts;
import com.gyuyeon.springbook.file.FileStore;
import com.gyuyeon.springbook.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final FileStore fileStore;

    @Transactional
    public List<ImageFile> saveImagesToPost(List<MultipartFile> imageFiles, Posts posts) throws IOException {
        List<ImageFile> storedImageFiles = fileStore.storeFiles(imageFiles, posts);
        for (ImageFile storedImageFile : storedImageFiles) {
            imageRepository.save(storedImageFile);
        }

        return storedImageFiles;
    }

    @Transactional(readOnly = true)
    public List<ImageFile> findByPostDesc(Posts posts) {
        return imageRepository.findByPostDesc(posts);
    }

    public ImageFile findById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 이미지 파일이 없습니다. id=" + id));
    }

    public UrlResource downloadImage(String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    public void deleteImage(Long id) {
        ImageFile image = imageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 이미지 파일이 없습니다. id=" + id));
        fileStore.deleteFile(image.getStoreFileName());
        imageRepository.delete(image);
    }

}
