package com.example.smartparkpj.controller;

import com.example.smartparkpj.dto.*;
import com.example.smartparkpj.service.AdminService;
import com.example.smartparkpj.service.EnterService;
import com.example.smartparkpj.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@Log4j2
@RequiredArgsConstructor
public class AdminController {
    final private AdminService adminService;

    final private TicketService ticketService;

    @GetMapping("")
    public String adminMain(){return "redirect:/admin/enter/admin_map";}

    @GetMapping("/enter/admin_map")
    public void getAdmin_map() {
        log.info("GetMapping /enter/admin_map ...");
    }

    @PostMapping("/enter/addAttraction")
    public String addAttraction(AttractionDTO attractionDTO, MarkerDTO markerDTO){

        log.info("addAttraction---------------- : " + attractionDTO + markerDTO);
        adminService.addAttraction(attractionDTO,markerDTO);

        return "redirect:/admin/enter/admin_map";

    }

    @PostMapping("/enter/addShop")
    public String addShop(ShopDTO shopDTO, MarkerDTO markerDTO){

        log.info("addShop---------------- : " + shopDTO + markerDTO);
        adminService.addShop(shopDTO,markerDTO);

        return "redirect:/admin/enter/admin_map";

    }

    @GetMapping("/ticket/adminTicket")
    public void managerListGet(Model model){
        log.info("managerList GET");
//
//        if(bindingResult.hasErrors()){
//            ticketDTO = ticketDTO.builder().build();
//        }
        model.addAttribute("ticketDTO", ticketService.getAll());
    }

    @GetMapping("/remove")
    public String managerListPost(int tno) {
        log.info("managerList POST");
        ticketService.remove(tno);
        return "redirect:/admin/ticket/adminTicket"; // 티켓 리스트 페이지로 리다이렉트
    }

    @GetMapping("/ticket/adminAddTicket")
    public void adminAddTicketGET(){
        log.info("adminAddTicketGE...!!!!");
    }

    @PostMapping("/ticket/adminAddTicket")
    public String adminAddTicket(@Valid TicketDTO ticketDTO,
                                 BindingResult bindingResult, HttpServletRequest httpServletRequest,
                                 RedirectAttributes redirectAttributes){
        log.info(ticketDTO);

        // 오류가 있을 경우 처리
        if(bindingResult.hasErrors()){
            // 예: redirectAttributes.addFlashAttribute("error", "주문 정보가 올바르지 않습니다.");
            return "redirect:/ticket/add"; // 다시 입력 폼으로 리다이렉트
        }

        // 주문 정보를 서비스에 전달
        ticketService.add(ticketDTO);

        return "redirect:/admin/ticket/adminTicket";
    }

}
