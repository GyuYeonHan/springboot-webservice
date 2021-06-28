package com.gyuyeon.springbook.domain.login;

import com.gyuyeon.springbook.domain.user.User;
import com.gyuyeon.springbook.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    /**
     * @param loginId
     * @param password
     * @return null이면 로그인 실패
     */
    public User login(String loginId, String password) {
        return userRepository.findByLoginId(loginId)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }
}
