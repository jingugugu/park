package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.MemberVO;
import com.example.smartparkpj.dto.MemberDTO;
import com.example.smartparkpj.mapper.MemberMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
public class MemberServiceTests {

    private PasswordEncoder passwordEncoder;

    @Autowired(required = false)
    private MemberService memberService;

    @Test
    public void testRegister() {
        MemberDTO memberDTO = MemberDTO.builder()
                .email_id("aaaa@test.com")
                .password("1111")
                .nickName("testNick")
                .member_name("testName")
                .phone("010-5678-7890")
                .addDate(LocalDateTime.now())
                .birthday("2000-04-04")
                .profileImg("testImg")
                .build();
        memberService.register(memberDTO);
    }

    @Test
    public void addMemberTest() {
        passwordEncoder = new BCryptPasswordEncoder();
        String email_id = "member4";
        MemberDTO memberDTO = MemberDTO.builder()
                .email_id(email_id)
                .password(passwordEncoder.encode("1111"))
                .nickName("라이언")
                .member_name("춘식이")
                .phone("010-1234-1234")
                .addDate(LocalDateTime.now())
                .birthday("2001-03-04")
                .profileImg("testImg")
                .del(false)
                .social(false).build();
        List<Integer> role_set = new ArrayList<>();
        role_set.add(0);
        role_set.add(1);
        memberService.addMember(memberDTO);
        String member_mid = "member4";
        memberService.addMemberRole(member_mid, role_set);
    }

    @Test
    public void nameCheck() {
        String name = "testName";
        MemberVO check = memberService.nameCheck(name);
        log.info(check);
        log.info(memberService.nameCheck(name));
    }
}
