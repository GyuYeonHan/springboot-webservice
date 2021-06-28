package com.gyuyeon.springbook.web;

import com.gyuyeon.springbook.service.posts.PostsService;
import com.gyuyeon.springbook.web.dto.PostsResponseDto;
import com.gyuyeon.springbook.web.dto.PostsSaveRequestDto;
import com.gyuyeon.springbook.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        return "posts";
    }

    @GetMapping("/save")
    public String saveFormView(Model model) {
        model.addAttribute("post", new PostsSaveRequestDto());
        return "savePostForm";
    }

    @PostMapping("/save")
    public String savePost(@Validated @ModelAttribute("post") PostsSaveRequestDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "savePostForm";
        }

        postsService.save(dto);
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String updateFormView(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "updatePostForm";
    }

    @PostMapping("/{id}")
    public String updatePost(@PathVariable Long id, @Validated @ModelAttribute("post") PostsUpdateRequestDto dto,
                         BindingResult bindingResult) {
        log.info("dto = {}", dto);
        if (bindingResult.hasErrors()) {
            return "updatePostForm";
        }
        postsService.update(id, dto);
        return "redirect:/posts";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postsService.delete(id);
        return "redirect:/posts";
    }
}
