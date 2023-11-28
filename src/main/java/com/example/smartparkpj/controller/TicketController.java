package com.example.smartparkpj.controller;

import com.example.smartparkpj.domain.OrderVO;
import com.example.smartparkpj.dto.OrderDTO;
import com.example.smartparkpj.dto.PageRequestDTO;
import com.example.smartparkpj.dto.TicketDTO;
import com.example.smartparkpj.service.OrderService;
import com.example.smartparkpj.service.TicketService;
//import jdk.javadoc.internal.tool.JavadocLog;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/ticket")
@Log4j2
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    private final OrderService orderService;

    @GetMapping("/list")
    public void list(Model model){
        log.info("list GET!!");

        model.addAttribute("ticketDTO", ticketService.getAll());
    }

    @PreAuthorize("isAuthenticated()")//로그인 한 사람만 접근 할수 있도록
    @GetMapping("/add")
    public void addGet(int tno, PageRequestDTO pageRequestDTO, Model model){
        TicketDTO ticketDTO = ticketService.getOne(tno);
        log.info("Add ticketDTO : "+ ticketDTO);

        model.addAttribute("ticketDTO", ticketDTO);
    }

    @PostMapping("/add")
    public String addPost(@Valid OrderDTO orderDTO,
                          BindingResult bindingResult, HttpServletRequest httpServletRequest,
                          RedirectAttributes redirectAttributes){
        // 오류가 있을 경우 처리
        if(bindingResult.hasErrors()){
            // 예: redirectAttributes.addFlashAttribute("error", "주문 정보가 올바르지 않습니다.");
            return "redirect:/ticket/add"; // 다시 입력 폼으로 리다이렉트
        }

        // 주문 정보를 서비스에 전달
        int ono = orderService.add(orderDTO);
        orderDTO.setOno(ono);
        // Flash Attribute를 사용하여 리다이렉트 시에 데이터를 전달
        redirectAttributes.addFlashAttribute("orderDTO", orderDTO);

        return "redirect:/ticket/addFinish";
    }

    @GetMapping("/addFinish")
    public void addFinish(@ModelAttribute("orderDTO") OrderDTO orderDTO, BindingResult bindingResult, Model model){
        // addFinish 페이지에서 OrderDTO 활용
        log.info("addFinish Test with orderDTO: " + orderDTO);
        // 여기에서 orderDTO를 활용하여 로직을 추가하거나 화면에 표시하는 등의 작업을 수행
        String email_id = orderDTO.getEmail_id();

        if(bindingResult.hasErrors()){
            orderDTO = orderDTO.builder().build();
        }

        log.info("addFinish Test : " + orderService.getOneAll(email_id));
        model.addAttribute("orderDTO", orderService.getOne(orderDTO.getOno()));
    }
}
