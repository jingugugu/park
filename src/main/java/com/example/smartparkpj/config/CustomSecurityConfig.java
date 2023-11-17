package com.example.smartparkpj.config;

import com.example.smartparkpj.security.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) // 어노테이션으로 권한을 설정할 수 있게 하는 어노테이션
public class CustomSecurityConfig {
    // 주입 필요
    private final DataSource dataSource;
    private final CustomUserDetailService customUserDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        log.info("------------------- Configure ------------------");

        // 커스텀 로그인 페이지
        httpSecurity.formLogin()
                .loginPage("/member/login"); // 로그인을 진행할 페이지

        httpSecurity.csrf().disable(); // CSRF 토큰을 비활성화

        // 자동 로그인 설정
        httpSecurity.rememberMe()
                .key("12345678")
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(customUserDetailService)
                .tokenValiditySeconds(60 * 60 * 24 * 30);

        return httpSecurity.build();
        // filterChain() 메소드가 동작하면 이전과 달리 /board/list 에 바로 접근 가능.
        // '/login' 에는 접근이 안 됨.
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        log.info("-------- Web Configure -------");

        // 정적 파일 경로에 시큐리티 적용을 안함
        return (web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
    }

    @Bean
    public PasswordEncoder passwordEncoder() { // 같은 문자열일 패스워드라도 매번 다른 해시 알고리즘으로 처리해 줌 (암호화)
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }
}
