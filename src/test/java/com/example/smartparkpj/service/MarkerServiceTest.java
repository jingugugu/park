package com.example.smartparkpj.service;

import com.example.smartparkpj.dto.MarkerDTO;
import com.example.smartparkpj.dto.TicketDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class MarkerServiceTest {
    @Autowired
    private MarkerService markerService;

    @Test
    public void getAllTest(){
        List<MarkerDTO> markerDTOS = markerService.getAll();
        log.info(markerDTOS);
    }
}
