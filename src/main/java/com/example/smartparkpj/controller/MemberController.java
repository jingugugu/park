package com.example.smartparkpj.controller;


import com.example.smartparkpj.dto.InquiryDTO;
import com.example.smartparkpj.dto.MemberDTO;
import com.example.smartparkpj.security.dto.MemberSecurityDTO;
import com.example.smartparkpj.dto.OrderDTO;
import com.example.smartparkpj.service.MailSenderService;
import com.example.smartparkpj.service.MemberService;
import com.example.smartparkpj.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.reflect.Member;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;
    private final MailSenderService mailSenderService;

    private final PasswordEncoder passwordEncoder;

    //로그인 할때 메서드 발생 이 필요한 부분
    private final OrderService orderService;

    //로그인 페이지, 회원가입 페이지
    //회원가입, 회원수정, 로그인, 로그아웃, 회원탈퇴
//    @PreAuthorize("hasRole('ADMIN')") // 관리자만 접근할수 있게 하는 코드
    @GetMapping("/login")
    public void login() {
        // 로그인 페이지 출력
        log.info("/member/login,,,,,");
    }

    @GetMapping("/register")
    public void registerGET(){
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

        return "redirect:/member/registerSuccess";
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

    @GetMapping("/edit")
    public void edit(Authentication authentication, Model model) {
        // Authentication : 유저의 identification 을 확인하는 절차이다 쉽게 설명하면 유저의 아이디와 비번을 확인하는 절차
        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
        String email_id = memberSecurityDTO.getEmail_id();

        MemberDTO memberDTO = memberService.getMember(email_id);

        model.addAttribute("memberDTO", memberDTO);
    }

    @PostMapping("/edit")
    public String editProcess(MemberDTO memberDTO, Authentication authentication) {
        log.info("editProcess()");
        log.info(memberDTO);

        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
        memberDTO.setEmail_id(memberSecurityDTO.getEmail_id());

        if (memberDTO.getPassword() == null || memberDTO.getPassword().isEmpty()) {
            log.info("================기존 비밀번호 사용================");
            memberDTO.setPassword(memberSecurityDTO.getPassword());
        } else {
            log.info("================비밀번호 변경================");
            memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
            // 세션에 저장된 정보 변경
            memberSecurityDTO.setPassword(memberDTO.getPassword());
        }
        memberService.edit(memberDTO);

        // 세션에 저장된 정보 변경
        memberSecurityDTO.setEmail_id(memberDTO.getEmail_id());

        return "redirect:/main/park_main";
    }

    @GetMapping("/removeRequest")
    public void removeRequestMember(Authentication authentication, Model model) {
        // Authentication : 유저의 identification 을 확인하는 절차이다 쉽게 설명하면 유저의 아이디와 비번을 확인하는 절차
        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
        String email_id = memberSecurityDTO.getEmail_id();

        MemberDTO memberDTO = memberService.getMember(email_id);

        model.addAttribute("memberDTO", memberDTO);
    }

    @PostMapping("/deleteReason")
    public String deleteReason(MemberDTO memberDTO) {
        log.info("================탈퇴 신청 사유 보냄================");
        memberService.deleteReason(memberDTO);

        return "redirect:/main/park_main";
    }

    @GetMapping("/findPassword")
    public void findPassword() {
        log.info("================비밀번호 찾기================");
    }

    @PostMapping("/findPassword")
    public String editPassword(MemberDTO memberDTO) {
        log.info("================비밀번호 찾기================");
        memberService.editPassword(memberDTO);

        return "redirect:/main/park_main";
    }

    @GetMapping("/registerSuccess")
    public void registerSuccess() {
        log.info("================회원가입 완료================");
    }

}
