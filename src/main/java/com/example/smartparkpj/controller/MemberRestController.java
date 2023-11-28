package com.example.smartparkpj.controller;

import com.example.smartparkpj.security.dto.MemberSecurityDTO;
import com.example.smartparkpj.service.MailSenderService;
import com.example.smartparkpj.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/member_rest")
@Log4j2
@RequiredArgsConstructor
@RestController
public class MemberRestController {
    private final MemberService memberService;
    private final MailSenderService mailSenderService;
    private final PasswordEncoder passwordEncoder;

    // 이메일 중복확인
    @GetMapping(value = "/emailCheck/{email_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String emailCheck(@PathVariable("email_id")String email_id) {
        log.info(memberService.emailCheck(email_id));
        if (memberService.emailCheck(email_id)) {
            return "true";
        } else {
            return "false";
        }
    }

    // 닉네임 중복확인
    @GetMapping(value = "/nickCheck/{nickName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> nickCheck(@PathVariable("nickName")String nickName) {
        Map<String, String> resultMap = new HashMap<>();
        if (memberService.nickCheck(nickName)) {
            resultMap.put("result", "true");
        } else {
            resultMap.put("result", "false");
        }
        return resultMap;
    }

    @GetMapping("/sendConfirmMail/{mailTo}")
    @ResponseBody
    public String sendConfirmMail(@PathVariable("mailTo") String mailTo) throws Exception {
        if (mailSenderService.sendMailByAddMember(mailTo)) {
            return mailSenderService.getConfirmKey();
        }
        else {
            return "false";
        }
    }

    @GetMapping("/sendConfirmPassword/{pw}")
    @ResponseBody
    public String sendConfirmPassword(@PathVariable("pw") String pw, Authentication authentication) throws Exception {
        // Authentication : 유저의 identification 을 확인하는 절차이다 쉽게 설명하면 유저의 아이디와 비번을 확인하는 절차
        log.info("checkPassword-----------------------------------" + authentication);
        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
        // Authentication 을 이용하여
        Boolean passMatch = passwordEncoder.matches(pw, memberSecurityDTO.getPassword());
        log.info("passMatch------------------------------------" + passMatch);
        if (!passMatch) {
            return "false";
        } else {
            return "true";
        }
    }
}
