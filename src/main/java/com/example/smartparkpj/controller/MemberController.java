package com.example.smartparkpj.controller;

import com.example.smartparkpj.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //로그인 페이지, 회원가입 페이지
    //회원가입, 회원수정, 로그인, 로그아웃, 회원탈퇴
}
