package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.TicketVO;
import com.example.smartparkpj.dto.TicketDTO;
import com.example.smartparkpj.mapper.TicketMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final ModelMapper modelMapperConfig;

    private final TicketMapper ticketMapper;
    @Override
    public List<TicketDTO> getAll() {
                List<TicketVO> voLsit = ticketMapper.selectAll();
        List<TicketDTO> dtoList = new ArrayList<>();
        for(TicketVO ticketVO : voLsit){
            TicketDTO ticketDTO = modelMapperConfig.map(ticketVO, TicketDTO.class);
            dtoList.add(ticketDTO);
        }
        return dtoList;
    }

    @Override
    public TicketDTO getOne(int tno) {
        TicketVO ticketVO = ticketMapper.selectOne(tno);
        TicketDTO ticketDTO = modelMapperConfig.map(ticketVO, TicketDTO.class);

        return ticketDTO;
    }

    @Override
    public void remove(int tno) {
        ticketMapper.delet(tno);
    }

    @Override
    public void add(TicketDTO ticketDTO) {
        log.info("service.....DTO" + ticketDTO);
        TicketVO ticketVO = modelMapperConfig.map(ticketDTO, TicketVO.class);
        log.info("service......" + ticketVO);
        ticketMapper.insert(ticketVO);
    }
}

