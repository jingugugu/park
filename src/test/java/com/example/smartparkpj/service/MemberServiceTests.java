package com.example.smartparkpj.service;

import com.example.smartparkpj.mapper.MemberMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class MemberServiceTests {
    @Autowired
    private MemberService memberService;
}