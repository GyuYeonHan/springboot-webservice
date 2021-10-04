package com.gyuyeon.springbook.domain.user;

import com.gyuyeon.springbook.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("유저 저장 테스트")
    void test1() {
        //given
        User user = User.builder()
                .name("userA")
                .role(Role.GUEST)
                .email("test@email.com")
                .loginId("testid")
                .password("testpassword")
                .build();

        //when
        User savedUser = userRepository.save(user);

        //then
        Assertions.assertThat(savedUser).isEqualTo(user);
    }

    @Test
    @DisplayName("유저 수정 테스트")
    void test2() {
        //given
        User user = User.builder()
                .name("userA")
                .role(Role.GUEST)
                .email("test@email.com")
                .loginId("testid")
                .password("testpassword")
                .build();

        User savedUser = userRepository.save(user);
        savedUser.setName("userB");

        //when
        User updatedUser = userRepository.save(savedUser);

        //then
        Assertions.assertThat(updatedUser.getName()).isEqualTo(savedUser.getName());
    }

    @Test
    @DisplayName("유저 삭제 테스트")
    void test3() {
        //given
        User user = User.builder()
                .name("userA")
                .role(Role.GUEST)
                .email("test@email.com")
                .loginId("testid")
                .password("testpassword")
                .build();

        User savedUser = userRepository.save(user);
        Long id = savedUser.getId();

        //when
        userRepository.delete(user);
        User deletedUser = userRepository.findById(id).orElse(null);

        //then
        Assertions.assertThat(deletedUser).isNull();
    }



}