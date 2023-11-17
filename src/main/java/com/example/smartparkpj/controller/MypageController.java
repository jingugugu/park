package com.example.smartparkpj.controller;

import com.example.smartparkpj.service.InquiryService;
import com.example.smartparkpj.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
@Log4j2
@RequiredArgsConstructor
public class MypageController {
    private final MypageService mypageService;

    @GetMapping("/profile")
    public void myPage() {
        log.info("GetMapping/profile ...");
    }
}
