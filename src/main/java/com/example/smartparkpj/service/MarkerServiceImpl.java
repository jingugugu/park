package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.MarkerVO;
import com.example.smartparkpj.domain.TicketVO;
import com.example.smartparkpj.dto.MarkerDTO;
import com.example.smartparkpj.dto.TicketDTO;
import com.example.smartparkpj.mapper.MarkerMapper;
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
public class MarkerServiceImpl implements MarkerService{

    private final ModelMapper modelMapperConfig;

    private final MarkerMapper markerMapper;

    @Override
    public List<MarkerDTO> getAll() {
        List<MarkerVO> voLsit = markerMapper.getMarkerList();
        List<MarkerDTO> dtoList = new ArrayList<>();
        for(MarkerVO markerVO : voLsit){
            MarkerDTO markerDTO = modelMapperConfig.map(markerVO, MarkerDTO.class);
            dtoList.add(markerDTO);
        }

        return dtoList;
    }
}
