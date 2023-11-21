package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.MemberVO;
import com.example.smartparkpj.domain.ReviewVO;
import com.example.smartparkpj.dto.PageRequestDTO;
import com.example.smartparkpj.dto.PageResponseDTO;
import com.example.smartparkpj.dto.ReviewDTO;
import com.example.smartparkpj.mapper.MemberMapper;
import com.example.smartparkpj.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;

    private final ModelMapper modelMapperConfig;

    private final MemberMapper memberMapper;

    @Override
    public PageResponseDTO<ReviewDTO>getList(PageRequestDTO pageRequestDTO) {
        List<ReviewVO> voList = reviewMapper.selectLsit(pageRequestDTO);

        List<ReviewDTO> dioList = new ArrayList<>();
        for(ReviewVO reviewVO : voList){

            int mno = reviewVO.getMno();
            MemberVO memberVO = memberMapper.selectOne(mno);
            String nickName = memberVO.getNickName();

            ReviewDTO reviewDTO = modelMapperConfig.map(reviewVO, ReviewDTO.class);
            reviewDTO.setNickName(nickName);
            dioList.add(reviewDTO);
            log.info(dioList);
        }

        int total = reviewMapper.getCount(pageRequestDTO);

        PageResponseDTO<ReviewDTO> pageResponseDTO = PageResponseDTO.<ReviewDTO>withAll()
                .dtoList(dioList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }


}
