package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.InquiryVO;
import com.example.smartparkpj.domain.MemberVO;
import com.example.smartparkpj.domain.OrderVO;
import com.example.smartparkpj.domain.ReviewVO;
import com.example.smartparkpj.dto.InquiryDTO;
import com.example.smartparkpj.dto.MemberDTO;
import com.example.smartparkpj.dto.OrderDTO;
import com.example.smartparkpj.dto.ReviewDTO;
import com.example.smartparkpj.mapper.MyPageMapper;
import groovyjarjarantlr4.v4.codegen.model.LabeledOp;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {

    private final ModelMapper modelMapper;

    private final MyPageMapper myPageMapper;

    @Override
    public List<OrderDTO> getMyOrder(String email_id) {
        List<OrderVO> voList = myPageMapper.myOrder(email_id);
        log.info(voList);
        List<OrderDTO> dtoList = new ArrayList<>();
        for (OrderVO orderVO : voList) {
            OrderDTO orderDTO = modelMapper.map(orderVO, OrderDTO.class);
            dtoList.add(orderDTO);
        }
        log.info("--------------getMyOrder ServiceImpl-------------------------");
        log.info(dtoList);
        return dtoList;
    }

    @Override
    public List<ReviewDTO> getMyReview(int mno) {
        List<ReviewVO> voList = myPageMapper.myReview(mno);
        log.info(voList);
        List<ReviewDTO> dtoList = new ArrayList<>();
        for (ReviewVO reviewVO : voList) {
            ReviewDTO reviewDTO = modelMapper.map(reviewVO, ReviewDTO.class);
            dtoList.add(reviewDTO);
        }
        log.info("--------------getMyReview ServiceImpl-------------------------");
        log.info(dtoList);
        return dtoList;
    }

    @Override
    public MemberDTO getMemberInformatrion(int mno) {
        MemberVO memberVO = myPageMapper.myPageInformation(mno);
        log.info(memberVO);
        MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
        log.info("-------------getMemberInformatrionServiceImpl---------------");
        log.info(memberDTO);
        return memberDTO;
    }

    @Override
    public List<InquiryDTO> getMyInquiry(int mno) {
        List<InquiryVO> voList = myPageMapper.myPageInquiry(mno);
        log.info(voList);
        List<InquiryDTO> dtoList = new ArrayList<>();
        for (InquiryVO inquiryVO : voList) {
            InquiryDTO inquiryDTO = modelMapper.map(inquiryVO, InquiryDTO.class);
            dtoList.add(inquiryDTO);
        }
        log.info("--------------getMyInquiry ServiceImpl-------------------------");
        log.info(dtoList);
        return dtoList;
    }


}
