package com.example.board.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //이미 생성된 사용자인지 신규 가입자 인지 판단하기 위한 메소드
    Optional<User> findByEmail(String email);
}
