package com.example.smartparkpj.controller;

import com.example.smartparkpj.dto.MarkerDTO;
import com.example.smartparkpj.dto.PageRequestDTO;
import com.example.smartparkpj.service.MarkerService;
import com.example.smartparkpj.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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

        log.info("시설 타입 : " + type);
        log.info("시설 넘버 : " + facility_no);

        log.info("pageRequestDTO" + reviewService.getList(pageRequestDTO));
        if(bindingResult.hasErrors()){
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        model.addAttribute("responseDTO", reviewService.getList(pageRequestDTO));
    }

    @GetMapping("/list")
    public void listGet(Model model){
        log.info("list GET!!");

        model.addAttribute("markerDTO", markerService.getAll());
    }

}
