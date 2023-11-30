package com.example.smartparkpj.security;

import com.example.smartparkpj.domain.MemberRole;
import com.example.smartparkpj.domain.MemberVO;
import com.example.smartparkpj.mapper.MemberMapper;
import com.example.smartparkpj.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("=========================userRequest=========================");
        log.info(userRequest);

        log.info("=========================oauth2 user=========================");
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();

        log.info("NAME : " + clientName);

        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        String email = null;
        switch (clientName) {
            case "kakao":
                email = getkakaoEmail(paramMap);
                break;
        }

        log.info("====================");
        log.info(email);
        log.info("====================");

//        paramMap.forEach((k, v) -> {
//            log.info("---------");
//            log.info(k + ":" + v);
//        });
        return generateDTO(email, paramMap);
    }

    private String getkakaoEmail(Map<String, Object> paramMap) {
        log.info("KAKAO=======================");

        Object value = paramMap.get("kakao_account");
        log.info(value);

        LinkedHashMap accountMap = (LinkedHashMap) value;
        String email = (String) accountMap.get("email");

        log.info("email======" + email);
        return email;
    }

    private MemberSecurityDTO generateDTO(String email, Map<String, Object> params) {
        MemberVO result = memberMapper.selectMember(email);

        // 데이터베이스에 해당 이메일의 사용자가 없다면
        if (result == null) {
            log.info("social member");
            // 회원 추가
            MemberVO memberVO = MemberVO.builder()
                    .email_id(email)
                    .nickName("KAKAO")
                    .member_name("KAKAO")
                    .phone("KAKAO")
                    .addDate(LocalDateTime.now())
                    .birthday("KAKAO")
                    .password(passwordEncoder.encode("1111"))
                    .social(true)
                    .build();
            memberVO.addRole(MemberRole.USER);
            memberMapper.addMember(memberVO);

            // MemberSecurityDTO 구성 및 반환
            List<Integer> memberRoles = memberMapper.selectRoles(email); // 해당 아이디의 role_set 번호 받아옴
            Collection<SimpleGrantedAuthority> grantedAuthorityArrayList = new ArrayList<>(); // 권한을 더해줄 배열 생성
            for (Integer role : memberRoles) {
                MemberRole memberRole = MemberRole.values()[role]; // MemberRole 안에 있는 values 값을 받아온 번호를 이용해서 권한 불러옴
                grantedAuthorityArrayList.add(new SimpleGrantedAuthority("ROLE_" + memberRole)); // 권한을 저장
            }
            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(memberVO.getMno(), email, "1111", true, grantedAuthorityArrayList);
            memberSecurityDTO.setProps(params);

            return memberSecurityDTO;
        }
        else {
            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
                    result.getMno(),
                    result.getEmail_id(),
                    result.getPassword(),
                    result.isSocial(),
                    result.getRoleSet().stream().map(
                            memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole)
                    ).collect(Collectors.toList())
            );
            return memberSecurityDTO;
        }
    }
}
