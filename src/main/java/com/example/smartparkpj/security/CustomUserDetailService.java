package com.example.smartparkpj.security;

import com.example.smartparkpj.domain.MemberVO;
import com.example.smartparkpj.mapper.MemberMapper;
import com.example.smartparkpj.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {



        log.info("loadUserByUsername" + username);

        // 작성한 매퍼를 이요해서 VO 객체를 반환
        MemberVO memberVO = memberMapper.selectMember(username);
        if (memberVO == null) {
            throw new UsernameNotFoundException("username not found,,,,");
        }
        // 권한 관련 객체 생성
        Collection<SimpleGrantedAuthority> grantedAuthorityArrayList = new ArrayList<>();
        grantedAuthorityArrayList.add(new SimpleGrantedAuthority("ROLE_USER")); // 우선 user 권한으로 지정

        // UserDetails 타입의 객체를 생성후 반환
        MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
                memberVO.getMno(),
                memberVO.getEmail_id(),
                memberVO.getPassword(),
                memberVO.getNickName(),
                memberVO.getMember_name(),
                memberVO.getPhone(),
                memberVO.getBirthday(),
                memberVO.getProfileImg(),
                grantedAuthorityArrayList
        );
        log.info(memberSecurityDTO);
        return memberSecurityDTO;

    }
}
