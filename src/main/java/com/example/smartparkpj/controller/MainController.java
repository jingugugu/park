package com.example.smartparkpj.controller;

import com.example.smartparkpj.dto.OrderDTO;
import com.example.smartparkpj.security.dto.MemberSecurityDTO;
import com.example.smartparkpj.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
@RequestMapping("/")
@Log4j2
@RequiredArgsConstructor
public class MainController {
    private final OrderService orderService;

    @GetMapping("")
    public String main(){
        return "redirect:/main/park_main";
    }
    @GetMapping("/main/park_main")
    public void mainGet(Authentication authentication){
        if(authentication != null){
            log.info("/main/park_main.html!~~!");
            log.info("autttt---------------" + authentication);

            MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO)authentication.getPrincipal();
            log.info("/main/park_main.html!~~!");

            String email = memberSecurityDTO.getEmail_id();

            //덥데이트 기간만료
            List<OrderDTO> orderDTOS = orderService.getOneAll(email);
            LocalDateTime toDay =  LocalDateTime.now();

            // 현재 날짜와 시간을 보고 finished, has_ability 업데이트
            for (OrderDTO dtoList : orderDTOS) {
                log.info("PostMapping2!!!");
                try {
                    LocalDateTime endDate = LocalDateTime.parse(dtoList.getEndDate());

                    if (endDate.isBefore(toDay)) {
                        log.info("PostMapping3!!!");
                        // endDate가 현재 날짜와 시간보다 이전인 경우에 수행할 작업 티켓 사용 가능 여부 를 0으로 바꿈
                        orderService.modifyFinished(email);
                    }
                    if (endDate.plusDays(3).isBefore(toDay)) {
                        log.info("PostMapping3.5!!!");
                        //endDate 로 부터 3일 뒤 의 날짜가 현재 날짜보다 더 전 시간이면 리뷰작성 가능 여부 를 0 으로 바꿈
                        orderService.modifyHasAbility(email);
                    }
                } catch (DateTimeParseException e) {
                    // 날짜 형식이 맞지 않을 경우 예외 처리
                    log.info("날짜 오류 내용 : " + e.getMessage());
                }
            }
        }

//        log.info("PostMapping4!!!");
//        return "redirect:/";
        log.info("/main/park_main.html!~~!2");
    }

}
