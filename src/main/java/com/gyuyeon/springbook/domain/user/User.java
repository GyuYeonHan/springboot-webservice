package com.gyuyeon.springbook.domain.user;

import com.gyuyeon.springbook.domain.BaseTimeEntity;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class User extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Builder
    public User(String name, String email, String picture, Role role, String loginId, String password) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.loginId = loginId;
        this.password = password;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
