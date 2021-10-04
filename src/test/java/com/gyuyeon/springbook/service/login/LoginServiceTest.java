package com.gyuyeon.springbook.service.login;

import com.gyuyeon.springbook.domain.user.Role;
import com.gyuyeon.springbook.domain.user.User;
import com.gyuyeon.springbook.repository.UserRepository;
import com.gyuyeon.springbook.service.LoginService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class LoginServiceTest {

    @Autowired
    LoginService loginService;

    @Autowired
    UserRepository userRepository;

    User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .name("userA")
                .role(Role.GUEST)
                .email("test@email.com")
                .loginId("testId")
                .password("testPassword")
                .build();

        User savedUser = userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인 테스트")
    void test1() {
        String loginId = user.getLoginId();
        String password = user.getPassword();

        User loginUser = loginService.login(loginId, password);

        //then
        Assertions.assertThat(loginUser).isEqualTo(user);
    }

    @Test
    @DisplayName("잘못된 ID를 입력 시 로그인 실패")
    void test2() {
        String loginId = "wrongId";
        String password = user.getPassword();

        User loginUser = loginService.login(loginId, password);

        //then
        Assertions.assertThat(loginUser).isNull();
    }

    @Test
    @DisplayName("잘못된 Password를 입력 시 로그인 실패")
    void test3() {
        String loginId = user.getLoginId();
        String password = "wrongPassword";

        User loginUser = loginService.login(loginId, password);

        //then
        Assertions.assertThat(loginUser).isNull();
    }

}