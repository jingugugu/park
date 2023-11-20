package com.example.smartparkpj.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Log4j2
@RequiredArgsConstructor
public class MainController {

    @GetMapping("")
    public String main(){
        return "redirect:/main/park_main";
    }
    @GetMapping("/main/park_main")
    public void mainGet(Authentication authentication){
        log.info("/main/park_main.html!~~!");
        log.info("autttt---------------" + authentication);
    }

}
