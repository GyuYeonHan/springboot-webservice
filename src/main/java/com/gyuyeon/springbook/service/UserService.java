package com.gyuyeon.springbook.service;

import com.gyuyeon.springbook.domain.user.User;
import com.gyuyeon.springbook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public boolean save(User user) {
        Optional<User> duplicated = userRepository.findByLoginId(user.getLoginId());
        if (duplicated.isPresent()) {
            return false;
        }

        return true;
    }
}
