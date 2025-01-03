package com.example.board.config.auth;

import com.example.board.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;
import static org.springframework.security.config.Customizer.withDefaults;


@RequiredArgsConstructor
@EnableWebSecurity // Sprint Security 설정 활성화
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable) //h2-console 화면 사용
                .headers().frameOptions().disable() //h2-console 화면 사용
                .and()
                .authorizeRequests(authorizeRequest ->
                        authorizeRequest
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher(("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile")
                                ).permitAll()
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher(("/api/v1/**")
                                ).hasRole(Role.USER.name())
                                )

                ) //URL 별 권한 관리
                .antMatcherspermitAll() //전체열람권한
                .antMatchers).hasRole(Role.USER.name()) //USER 권한을 가진 사람만
                .anyRequest().authenticated() //나머지 URL은 인증(로그인)된 사용자만 허용
                .and()
                .logout()
                .logoutSuccessUrl("/") //로그아웃 성공 시 / 주소로 이동
                .and()
                .oauth2Login() //OAuth2 로그인 기능 설정
                .userInfoEndpoint()
                .userService(customOAuth2UserService); //구현체 등록
        return http.build();
    }


    }

}
