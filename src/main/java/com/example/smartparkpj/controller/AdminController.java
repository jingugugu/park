package com.example.smartparkpj.controller;

import com.example.smartparkpj.dto.AttractionDTO;
import com.example.smartparkpj.dto.FacilityDTO;
import com.example.smartparkpj.dto.MarkerDTO;
import com.example.smartparkpj.service.EnterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@Log4j2
@RequiredArgsConstructor
public class AdminController {
    final private EnterService enterService;

    @GetMapping("")
    public String adminMain(){return "redirect:/admin/enter/admin_map";}

    @GetMapping("/enter/admin_map")
    public void getAdmin_map() {
        log.info("GetMapping /enter/admin_map ...");
    }

    @PostMapping("/enter/addAttraction")
    public String addAttraction(AttractionDTO attractionDTO, MarkerDTO markerDTO){

        log.info("addAttraction---------------- : " + attractionDTO + markerDTO);
//        int marker_no = enterService.add(facilityDTO);

//        resultMap.put("marker_no",marker_no);
        return "redirect:/admin/enter/admin_map";

    }
}
