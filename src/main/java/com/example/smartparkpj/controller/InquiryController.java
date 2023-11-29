package com.example.smartparkpj.controller;

import com.example.smartparkpj.dto.InquiryDTO;
import com.example.smartparkpj.security.dto.MemberSecurityDTO;
import com.example.smartparkpj.service.InquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/inquiry")
@Log4j2
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    @GetMapping("/support")
    public void myInquiry(Authentication authentication, Model model) {
        log.info("GetMapping/support ...");
        int mno = 0;
        if (authentication != null) {
            MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
            mno = memberSecurityDTO.getMno();
        }

        List<InquiryDTO> inquiryDTOS = inquiryService.getAll(mno);
        log.info("---------------------------support/controller------------------------------");
        log.info(inquiryDTOS);
        model.addAttribute("inquiryDTO", inquiryService.getAll(mno));
    }

    @GetMapping("/add")
    public void getAddInquiry() {
        log.info("GetMapping/add ...");
    }

    @PostMapping("/add")
    public String postAddInquiry(InquiryDTO inquiryDTO, Authentication authentication, Model model) {
        log.info("inquiryDTO = " + inquiryDTO);

        int mno = 0;
        if (authentication != null) {
            MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
            mno = memberSecurityDTO.getMno();
        }

        inquiryDTO.setMno(mno);
        inquiryService.add(inquiryDTO);
        return "redirect:/inquiry/support";
    }

    @GetMapping({"/read", "modify"})
    public void getReadInquiry(int ino, Model model) {
        log.info("GetMapping/read ...");
        log.info(ino);

        InquiryDTO inquiryDTO = inquiryService.getOne(ino);
        int ss = inquiryDTO.getIno();
        log.info("ss!!!!!! " + ss);
        model.addAttribute("inquiryDTO", inquiryService.getOne(ino));
    }
    @PreAuthorize("principal.mno == #inquiryDTO.mno") // 작성자 정보가 일치 해야만 작업이 가능함.
    @PostMapping("/modify")
    public String  modifyInquiry(InquiryDTO inquiryDTO, BindingResult bindingResult, Model model) {
        log.info("PostMapping/modify...");

        int ino = inquiryDTO.getIno();

        if (bindingResult.hasErrors()) {
            log.info("has error...");

            return "redirect:/inquiry/modify?";
        }

        model.addAttribute("inquiryDTO", inquiryService.getOne(ino));
        inquiryService.modify(inquiryDTO);

        return "redirect:/inquiry/support";
    }

    @PreAuthorize("principal.mno == #inquiryDTO.mno")  // 작성자 정보가 일치 해야만 작업이 가능함.
    @PostMapping("/remove")
    public String removeInquiry(InquiryDTO inquiryDTO) {
        log.info("PostMapping/remove...");

        int ino = inquiryDTO.getIno();
        inquiryService.remove(ino);

        return "redirect:/inquiry/support";
    }

   /* @GetMapping("/addAnswer")
    public void addAnswerGet() {
        log.info("addAnswerGET...");
    }

    @PostMapping("/addAnswer")
    public void addAnswerPost() {
        log.info("addAnswerPOST...");
    }*/


}
