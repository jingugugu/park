package com.example.smartparkpj.controller;

import com.example.smartparkpj.dto.OrderDTO;
import com.example.smartparkpj.dto.PageRequestDTO;
import com.example.smartparkpj.dto.TicketDTO;
import com.example.smartparkpj.service.OrderService;
import com.example.smartparkpj.service.TicketService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/ticket")
@Log4j2
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    private final OrderService orderService;

    @GetMapping("/list")
    public void list(@Valid TicketDTO ticketDTO, BindingResult bindingResult, Model model){
        log.info(ticketDTO);

        if(bindingResult.hasErrors()){
            ticketDTO = ticketDTO.builder().build();
        }
        model.addAttribute("ticketDTO", ticketService.getAll());
    }

    @GetMapping("/add")
    public void addGet(int tno, PageRequestDTO pageRequestDTO, Model model){
        TicketDTO ticketDTO = ticketService.getOne(tno);
        log.info("Add ticketDTO : "+ ticketDTO);

        model.addAttribute("ticketDTO", ticketDTO);
    }

    @PostMapping("/add")
    public String addPost(OrderDTO orderDTO, HttpServletRequest httpServletRequest){
        log.info("addPost : " + orderDTO);

        orderService.add(orderDTO);
        return "redirect:/ticket/list";
    }
}
