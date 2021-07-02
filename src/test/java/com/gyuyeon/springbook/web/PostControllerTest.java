package com.gyuyeon.springbook.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

//    @Test // 로그인 체크 인터셉터 추가로 비활성화
    void 메인페이지_로딩() {
        //when
        String body = this.restTemplate.getForObject("/posts", String.class);

        //then
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
    }
}