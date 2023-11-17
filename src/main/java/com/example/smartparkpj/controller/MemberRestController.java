package com.example.smartparkpj.controller;

import com.example.smartparkpj.service.MailSenderService;
import com.example.smartparkpj.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/member_rest")
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
}
