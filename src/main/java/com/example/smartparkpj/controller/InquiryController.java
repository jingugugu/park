package com.example.smartparkpj.controller;

import com.example.smartparkpj.dto.InquiryDTO;
import com.example.smartparkpj.service.InquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inquiry")
@Log4j2
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    @GetMapping("/support")
    public void myInquiry() {
        log.info("GetMapping/support ...");
    }

    @GetMapping("/add")
    public void getAddInquiry() {
        log.info("PostMapping/add ...");

    }

    @PostMapping("/add")
    public String postAddInquiry(InquiryDTO inquiryDTO) {
        log.info("inquiryDTO = " + inquiryDTO);

        inquiryService.add(inquiryDTO);

        return "redirect:/inquiry/support";
    }

    @GetMapping("read")
    public void getReadInquiry() {
        log.info("GetMapping/read ...");
    }

}
