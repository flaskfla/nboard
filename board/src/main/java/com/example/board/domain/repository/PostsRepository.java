package com.example.board.domain.repository;

import com.example.board.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    //Posts 클래스로 database 접근 가능하게 해줄 JpaRepository
    //JpaReposity<Entity 클래스, PK타입> 상속하면 자동으로 CRUD 메소드 생성
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
