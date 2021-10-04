package com.gyuyeon.springbook.service;

import com.gyuyeon.springbook.domain.user.User;
import com.gyuyeon.springbook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    /**
     * @param loginId
     * @param password
     * @return null이면 로그인 실패
     */
    @Transactional(readOnly = true)
    public User login(String loginId, String password) {
        return userRepository.findByLoginId(loginId)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }
}
