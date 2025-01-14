package com.gyuyeon.springbook.web;

import com.gyuyeon.springbook.domain.Comments;
import com.gyuyeon.springbook.domain.post.Posts;
import com.gyuyeon.springbook.domain.user.User;
import com.gyuyeon.springbook.service.CommentsService;
import com.gyuyeon.springbook.service.ImageService;
import com.gyuyeon.springbook.service.PostsService;
import com.gyuyeon.springbook.web.argumentresolver.Login;
import com.gyuyeon.springbook.web.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostsService postsService;
    private final CommentsService commentsService;
    private final ImageService imageService;

    @GetMapping
    public String listPosts(Model model, @RequestBody(required = false) Boolean isSaved, @RequestBody(required = false) Boolean isDeleted) {
        List<PostsListResponseDto> postDtoList = postsService.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());

        model.addAttribute("posts", postDtoList);

        if (isSaved != null && isSaved) {
            model.addAttribute("isSaved", true);
        }

        if (isDeleted != null && isDeleted) {
            model.addAttribute("isDeleted", true);
        }

        return "posts/posts";
    }

    @GetMapping("/save")
    public String savePostForm(@Login User user, Model model) {
        PostsSaveRequestDto dto = new PostsSaveRequestDto();
        if (user != null) {
            dto.setAuthor(user.getName());
        }

        model.addAttribute("post", dto);
        return "posts/savePostForm";
    }

    @PostMapping("/save")
    public String savePost(@Validated @ModelAttribute("post") PostsSaveRequestDto dto, BindingResult bindingResult, RedirectAttributes redirect) throws IOException {
        if (bindingResult.hasErrors()) {
            return "posts/savePostForm";
        }

        Long savedPostId = postsService.save(dto);
        Posts post = postsService.findById(savedPostId);

        List<MultipartFile> imageFiles = dto.getImageFiles();
        imageService.saveImagesToPost(imageFiles, post);

        redirect.addFlashAttribute("isSaved", true);
        return "redirect:/posts";
    }

    @PostMapping("/comment/save/{postId}")
    @ResponseBody
    public Long saveComment(@PathVariable Long postId,
                              @RequestBody CommentsSaveRequestDto dto) {
        Posts post = postsService.findById(postId);

        Comments comments = dto.toEntity();
        comments.setPosts(post);

        return commentsService.save(comments);
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model, @Login User user) {
        Posts posts = postsService.findById(id);
        PostsResponseDto postDto = new PostsResponseDto(posts);
        List<Comments> commentsList = posts.getCommentsList();
        List<String> content = Arrays.asList(posts.getContent().split("\n"));

        model.addAttribute("post", postDto);
        model.addAttribute("postContent", content);
        model.addAttribute("comments", commentsList);

        CommentsSaveRequestDto commentDto = new CommentsSaveRequestDto();
        if (user == null) {
            commentDto.setWriter("익명의 글쓴이");
        } else {
            commentDto.setWriter(user.getName());
        }
        model.addAttribute("commentForm", commentDto);

        return "posts/viewPost";
    }

    @GetMapping("/update/{id}")
    public String updatePostForm(@PathVariable Long id, Model model) {
        Posts posts = postsService.findById(id);
        PostsResponseDto dto = new PostsResponseDto(posts);
        model.addAttribute("post", dto);

        return "posts/updatePostForm";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable Long id, @Validated @ModelAttribute("post") PostsUpdateRequestDto dto,
                             BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "posts/updatePostForm";
        }
        Posts updatedPosts = postsService.update(id, dto);

        List<MultipartFile> imageFiles = dto.getImageFiles();
        imageService.saveImagesToPost(imageFiles, updatedPosts);

        return "redirect:/posts/{id}";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id, RedirectAttributes redirect) {
        postsService.delete(id);
        redirect.addFlashAttribute("isDeleted", true);

        return "redirect:/posts";
    }
}
