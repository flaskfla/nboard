package com.example.board.config.auth.dto;

import com.example.board.domain.user.Role;
import com.example.board.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object>attributes,String nameAttributeKey,String name,String email,String picture){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String,Object>attributes){
        return ofGoogle(userNameAttributeName,attributes);
        //OAuth2User에서 사용자 정보를 Map으로 반환하길래 값을 변경
    }
    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object>attributes){
        return OAuthAttributes.builder()
                .name((String)attributes.get("name"))
                .email((String)attributes.get("email"))
                .picture((String)attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity(){ //처음 가입할때 엔터티 생성
        return User.builder()
                    .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();

    }
}
