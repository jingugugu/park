package com.example.smartparkpj.controller;

import com.example.smartparkpj.dto.MarkerDTO;
import com.example.smartparkpj.dto.MemberDTO;
import com.example.smartparkpj.dto.PageRequestDTO;
import com.example.smartparkpj.dto.ReviewDTO;
import com.example.smartparkpj.security.dto.MemberSecurityDTO;
import com.example.smartparkpj.service.MarkerService;
import com.example.smartparkpj.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.function.BinaryOperator;

@Controller
@RequestMapping("/review")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private final MarkerService markerService;

    @GetMapping("/read")
    public void readGet(PageRequestDTO pageRequestDTO, MarkerDTO markerDTO, BindingResult bindingResult, Model model){
        log.info("review Get !!!!");

        String type = markerDTO.getType();
        int facility_no = markerDTO.getFacility_no();

        pageRequestDTO.setType(type);
        pageRequestDTO.setFacility_no(facility_no);

        log.info("시설 타입 : " + type);
        log.info("시설 넘버 : " + facility_no);

        log.info("pageRequestDTO" + reviewService.getList(pageRequestDTO));

        log.info("review 별점 평균 : " + reviewService.reviewScore(facility_no, type));//별점 평균 점수
        if(bindingResult.hasErrors()){
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        model.addAttribute("markerDTO", markerDTO);
        model.addAttribute("responseDTO", reviewService.getList(pageRequestDTO));
        model.addAttribute("reviewScore", reviewService.reviewScore(facility_no, type));//평균점수
    }



    @GetMapping("/list")
    public void listGet(Model model){
        log.info("list GET!!");

        model.addAttribute("markerDTO", markerService.getAll());
    }

    @PreAuthorize("isAuthenticated()")//로그인 한 사람만 접근 할수 있도록
    @GetMapping("/add")
    public void add(MarkerDTO markerDTO){
        log.info("review add GET");

        log.info("마커 확인용 로그 : " + markerDTO.getType());
        log.info("마커 확인용 로그 : " + markerDTO.getFacility_no());
    }

    @PostMapping("/add")
    public String add(ReviewDTO reviewDTO, MemberDTO memberDTO){
        log.info("memberDTO PostMapping mno: " + memberDTO.getEmail_id());
        log.info("reviewDTO PostMapping : " + reviewDTO);

        MemberDTO memberDTO1 = reviewService.setOne(memberDTO.getEmail_id());
        int mno = memberDTO1.getMno();

        reviewDTO.setMno(mno);

        reviewService.insert(reviewDTO);

        return "redirect:/review/list";
    }

    @GetMapping("/delet")
    public String delet(ReviewDTO reviewDTO){
        int rno = reviewDTO.getRno();
        reviewService.delet(rno);

        return "redirect:/review/list";
    }
}
