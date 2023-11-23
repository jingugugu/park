package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.InquiryVO;
import com.example.smartparkpj.domain.TicketVO;
import com.example.smartparkpj.dto.InquiryDTO;
import com.example.smartparkpj.dto.TicketDTO;
import com.example.smartparkpj.mapper.InquiryMapper;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService{

    private final ModelMapper modelMapper;

    private final InquiryMapper inquiryMapper;

    @Override
    public List<InquiryDTO> getAll(int mno) {
        List<InquiryVO> voLsit = inquiryMapper.selectAll(mno);
        log.info(voLsit);
        List<InquiryDTO> dtoList = new ArrayList<>();
        for(InquiryVO inquiryVO : voLsit){
            InquiryDTO inquiryDTO = modelMapper.map(inquiryVO, InquiryDTO.class);
            dtoList.add(inquiryDTO);
        }
        log.info("---------------------------------------");
        log.info(dtoList);
        return dtoList;
    }

    @Override
    public List<Date> getFormattedDate() {
        return inquiryMapper.selectFormattedDate();
    }

    @Override
    public InquiryDTO getOne(int ino) {
        InquiryVO inquiryVO = inquiryMapper.selectOne(ino);
        /*log.info("---------------------------------------");*/
        log.info(inquiryVO);
        InquiryDTO inquiryDTO = modelMapper.map(inquiryVO, InquiryDTO.class);
        log.info("---------------------------------------");
        log.info(inquiryDTO);


        return inquiryDTO;
    }

    @Override
    public void modify(InquiryDTO inquiryDTO) {
        log.info("inquiryDTO = " + inquiryDTO);
        InquiryVO inquiryVO = modelMapper.map(inquiryDTO, InquiryVO.class);
        inquiryMapper.updateInquiry(inquiryVO);

        log.info(inquiryVO);
    }

    @Override
    public void remove(int ino) {
        inquiryMapper.deleteInquiry(ino);
    }

    @Override
    public void add(InquiryDTO inquiryDTO) {
        log.info("inquiryDTO = " + inquiryDTO);
        InquiryVO inquiryVO = modelMapper.map(inquiryDTO, InquiryVO.class);
        log.info("inquiryVO = " + inquiryVO);

        inquiryMapper.addInquiry(inquiryVO);
    }

    @Override
    public void updateAnswer(InquiryDTO inquiryDTO) {
        log.info("updateAnswer = " + inquiryDTO.getAnswer());
        log.info("-----------------------------------------");

        InquiryVO inquiryVO = modelMapper.map(inquiryDTO, InquiryVO.class);
        log.info(inquiryVO);

        inquiryMapper.adminAnswer(inquiryVO);
    }

    @Override
    public List<InquiryDTO> getAdminListAll() {
        log.info("getAdminListAll...");
        List<InquiryVO> inquiryVOList = inquiryMapper.adminInquiryList();
        List<InquiryDTO> inquiryDTOList = new ArrayList<>();

        for (InquiryVO inquiryVO : inquiryVOList) {
            InquiryDTO inquiryDTO = modelMapper.map(inquiryVO, InquiryDTO.class);
            inquiryDTOList.add(inquiryDTO);
        }
        return inquiryDTOList;
    }

    @Override
    public void adminRemove(int ino) {
        inquiryMapper.adminInquiryDelete(ino);
    }

}
