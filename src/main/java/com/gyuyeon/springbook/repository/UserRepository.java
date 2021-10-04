package com.gyuyeon.springbook.repository;

import com.gyuyeon.springbook.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByLoginId(String loginId);

}
