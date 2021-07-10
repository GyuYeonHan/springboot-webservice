package com.gyuyeon.springbook.web.user;

import com.gyuyeon.springbook.domain.user.Role;
import com.gyuyeon.springbook.domain.user.User;
import com.gyuyeon.springbook.domain.user.UserRepository;
import com.gyuyeon.springbook.service.user.UserService;
import com.gyuyeon.springbook.web.user.form.UserSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("user") User user) {
        return "users/addUserForm";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute("user") UserSaveForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/addUserForm";
        }

        User user = User.builder()
                .role(Role.GUEST)
                .name(form.getName())
                .email(form.getEmail())
                .loginId(form.getLoginId())
                .password(form.getPassword())
                .build();

        userService.save(user);
        return "redirect:/";
    }
}
