package com.example.smartparkpj.service;

import com.example.smartparkpj.dto.TicketDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class TicketServiceTests {
    @Autowired
    private TicketService ticketService;

    @Test
    public void getAllTest(){
        List<TicketDTO> ticketDTOS = ticketService.getAll();
        log.info(ticketDTOS);
    }

    @Test
    public void getOneTest(){
        TicketDTO ticketDTO = ticketService.getOne(4);
        log.info(ticketDTO);
    }
}
