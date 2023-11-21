package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.TicketVO;
import com.example.smartparkpj.dto.TicketDTO;

import java.util.List;

public interface TicketService {

    List<TicketDTO> getAll();

    TicketDTO getOne(int tno);

    void remove(int tno);

    void add(TicketDTO ticketDTO);
}
