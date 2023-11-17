package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.TicketVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class TicketMapperTests {
    @Autowired
    private TicketMapper ticketMapper;

    @Test
    public void selctAllTest(){
        List<TicketVO> ticketVOS = ticketMapper.selectAll();
        log.info(ticketVOS);
    }

    @Test
    public void selctOne(){
        TicketVO ticketVO = ticketMapper.selectOne(2);
        log.info(ticketVO);
    }


}
