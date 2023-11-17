package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.InquiryVO;
import com.example.smartparkpj.dto.InquiryDTO;
import com.example.smartparkpj.mapper.InquiryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService{

    private final ModelMapper modelMapper;

    private final InquiryMapper inquiryMapper;

    @Override
    public void add(InquiryDTO inquiryDTO) {
        log.info("inquiryDTO = " + inquiryDTO);
        InquiryVO inquiryVO = modelMapper.map(inquiryDTO, InquiryVO.class);
        log.info("inquiryVO = " + inquiryVO);

        inquiryMapper.addInquiry(inquiryVO);
    }
}
