package com.example.smartparkpj.security;

import com.example.smartparkpj.domain.MemberRole;
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
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername" + username);

        // 작성한 매퍼를 이용해서 VO 객체를 반환
        MemberVO memberVO = memberMapper.selectMember(username);
        if (memberVO == null) {
            log.info("==================로그인 실패==================");
            throw new UsernameNotFoundException("username not found,,,,");
        }

        // 권한 관련 객체 생성
        List<Integer> memberRoles = memberMapper.selectRoles(username); // 해당 아이디의 role_set 번호 받아옴
        Collection<SimpleGrantedAuthority> grantedAuthorityArrayList = new ArrayList<>(); // 권한을 더해줄 배열 생성
        for (Integer role : memberRoles) {
            MemberRole memberRole = MemberRole.values()[role]; // MemberRole 안에 있는 values 값을 받아온 번호를 이용해서 권한 불러옴
            grantedAuthorityArrayList.add(new SimpleGrantedAuthority("ROLE_" + memberRole)); // 권한을 저장
        }

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
