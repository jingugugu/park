package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.OrderVO;
import com.example.smartparkpj.dto.OrderDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void getOneTest(){
        List<OrderDTO> orderDTO = orderService.getOneAll("testAdd@naver.com");

        log.info("getOneTest:" + orderDTO);
    }

    @Test
    public void modifyTest(){
        String mail = "test@naver.com235";
        orderService.modifyFinished(mail);
    }

    @Test
    public void modifyHasAbility(){
        String mail = "gojioon12313@naver.com";
        orderService.modifyHasAbility(mail);
    }
}
