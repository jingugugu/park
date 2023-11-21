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

    @Test
    public void deletTest(){
        ticketMapper.delet(6);
    }

    @Test
    public void insertTest(){
        TicketVO ticketVO = TicketVO.builder()
                .tno(6)
                .tname("이벤트 멤버쉽")
                .tinfo("12~22시 사용가능")
                .tprice(35000)
                .ticket_type("event")
                .use_period(12)
                .build();
        ticketMapper.insert(ticketVO);
    }
}
