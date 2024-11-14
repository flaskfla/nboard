package com.example.board.domain.posts;

import com.example.board.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    //실제 DB 테이블과 매칭이되는 클래스로 보통 Entity 클래스라고 함
    //JPA를 사용하면 실제 쿼리를 날리는것이 아닌 이 Entity 클래스의 수정으로 DB작업을 함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //생성자 생성 위에 선언시 생성자에 포함된 필드만 빌더 (Setter X)
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
    // update 함수 추가
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
