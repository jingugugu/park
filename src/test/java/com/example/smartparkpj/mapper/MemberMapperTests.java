package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.MemberVO;
import com.example.smartparkpj.dto.MemberDTO;
import lombok.Builder;
import lombok.ToString;
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
public class MemberMapperTests {
    @Autowired(required = false)
    private MemberMapper memberMapper;

    private PasswordEncoder passwordEncoder;

    @Test
    public void testRegister() {
        MemberVO memberVO = MemberVO.builder()
                .email_id("test@test.com")
                .password("1234")
                .nickName("라이언")
                .member_name("춘식이")
                .phone("010-1234-1234")
                .addDate(LocalDateTime.now())
                .birthday("2001-03-04")
                .profileImg("testImg")
                .build();
        memberMapper.register(memberVO);
    }

//    @Test
//    public void addMemberTest() {
//        passwordEncoder = new BCryptPasswordEncoder();
//        String email_id = "member2";
//        MemberVO memberVO = MemberVO.builder()
//                .email_id(email_id)
//                .password(passwordEncoder.encode("1111"))
//                .nickName("라이언")
//                .member_name("춘식이")
//                .phone("010-1234-1234")
//                .addDate(LocalDateTime.now())
//                .birthday("2001-03-04")
//                .profileImg("testImg")
//                .del(false)
//                .social(false).build();
//        List<Integer> role_set = new ArrayList<>();
//        role_set.add(0);
//        role_set.add(1);
////        memberMapper.addMember(memberVO);
//        String member_mid = "member2";
//        memberMapper.addMemberRole(member_mid, role_set);
//    }
    @Test
    public void nameCheck() {
        String name = "testName";
        int check = memberMapper.nameCheck(name);
        log.info(check);
    }

    @Test
    public void nickCheck() {
        String nick = " testName";
        int check = memberMapper.nickCheck(nick);
        log.info(check);
    }

    @Test
    public void selectMemberTest() {
        MemberVO memberVO = memberMapper.selectMember("test");
        log.info(memberVO);
    }

    @Test
    public void selectOneTest(){
        MemberVO memberVO = memberMapper.selectOne(3);
        log.info(memberVO);
    }
}
