package com.example.smartparkpj.controller;

import com.example.smartparkpj.service.MailSenderService;
import com.example.smartparkpj.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/member_rest") // localhost:8080/member_rest/sendConfirmMail?mailTo=jingu8751@naver.com
@Log4j2
@RequiredArgsConstructor
@RestController
public class MemberRestController {
    private final MemberService memberService;
    private final MailSenderService mailSenderService;

    @GetMapping(value = "/nameCheck/{member}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> nameCheck(@PathVariable("member")String member) {
        Map<String, String> resultMap = new HashMap<>();
        log.info(memberService.nameCheck(member));
        if (memberService.nameCheck(member)) {
            resultMap.put("result", "true");
        } else {
            resultMap.put("result", "false");
        }
        return resultMap;
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

}
