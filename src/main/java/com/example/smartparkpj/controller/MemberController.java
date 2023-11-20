package com.example.smartparkpj.controller;

import com.example.smartparkpj.domain.MemberVO;
import com.example.smartparkpj.dto.MemberDTO;
import com.example.smartparkpj.service.MailSenderService;
import com.example.smartparkpj.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;
    private final MailSenderService mailSenderService;

    //로그인 페이지, 회원가입 페이지
    //회원가입, 회원수정, 로그인, 로그아웃, 회원탈퇴
//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/login")
    public void login() {
        log.info("/member/login,,,,,");
    }


    @GetMapping("/register")
    public void registerGET(String error, String logout){
        log.info("/member/registerGET,,,,,");
        log.info("logout: " + logout);
    }

    @GetMapping("/register2")
    public void registerGET2(){
        log.info("/member/registerGET2,,,,,");
    }
    @PostMapping("/register")
    public String registerPOST(@Valid MemberDTO memberDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("/member/registerPOST,,,,,");
//        if (bindingResult.hasErrors()) { // 에러가 발생한 경우
//            log.info("registerPOST has error");
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
//            return "redirect:/member/register";
//        }
        log.info(memberDTO);
        memberService.addMember(memberDTO);

        return "redirect:/member/login";
    }

    @GetMapping("/sendConfirmMail")
    @ResponseBody
    public String sendConfirmMail(String mailTo, HttpSession session) throws Exception {
        if (mailSenderService.sendMailByAddMember(mailTo)) {
            session.setAttribute("confirmKey", mailSenderService.getConfirmKey());
            return "true";
        }
        else {
            return "false";
        }
    }



}
