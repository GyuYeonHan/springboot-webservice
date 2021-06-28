package com.gyuyeon.springbook.web.user.form;

import com.gyuyeon.springbook.domain.user.Role;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserSaveForm {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;
}
