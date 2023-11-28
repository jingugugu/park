package com.example.smartparkpj.controller;

import com.example.smartparkpj.dto.InquiryDTO;
import com.example.smartparkpj.dto.MemberDTO;
import com.example.smartparkpj.dto.OrderDTO;
import com.example.smartparkpj.dto.ReviewDTO;
import com.example.smartparkpj.security.dto.MemberSecurityDTO;
import com.example.smartparkpj.service.InquiryService;
import com.example.smartparkpj.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mypage")
@Log4j2
@RequiredArgsConstructor
public class MypageController {
    private final MypageService mypageService;

    @GetMapping("/profile")
    public void profile(Authentication authentication, Model model) {
        log.info("GetMapping/profile ...");
        int mno = 0;
        if (authentication != null) {
            MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
            mno = memberSecurityDTO.getMno();
        }

        model.addAttribute("MemberDTO", mypageService.getMemberInformatrion(mno));
    }

    @GetMapping("/order")
    public void order(Authentication authentication, Model model) {
        log.info("GetMapping/order ...");

        String email_id = "";
        if (authentication != null) {
            MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
            email_id = memberSecurityDTO.getEmail_id();
        }

        model.addAttribute("orderDTO", mypageService.getMyOrder(email_id));
        log.info(mypageService);
    }

    @GetMapping("/review")
    public void review(Authentication authentication, Model model) {
        log.info("GetMapping/review ...");

        int mno = 0;
        if (authentication != null) {
            MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
            mno = memberSecurityDTO.getMno();
        }

        model.addAttribute("reviewDTO", mypageService.getMyReview(mno));
    }

    @GetMapping("inquiry")
    public void inquiry(Authentication authentication, Model model) {
        log.info("GetMapping/inquiry");

        int mno = 0;
        if (authentication != null) {
            MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
            mno = memberSecurityDTO.getMno();
        }

        model.addAttribute("inquiryDTO", mypageService.getMyInquiry(mno));
    }

}
