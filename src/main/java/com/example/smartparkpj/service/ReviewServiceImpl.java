package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.MemberVO;
import com.example.smartparkpj.domain.ReviewVO;
import com.example.smartparkpj.domain.TicketVO;
import com.example.smartparkpj.dto.*;
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
        List<ReviewVO> voList = reviewMapper.selectList(pageRequestDTO);

        List<ReviewDTO> dioList = new ArrayList<>();
        for(ReviewVO reviewVO : voList){

            int mno = reviewVO.getMno();
            MemberVO memberVO = memberMapper.selectOne(mno);
            String nickName = memberVO.getNickName();
            String email_id = memberVO.getEmail_id();

            ReviewDTO reviewDTO = modelMapperConfig.map(reviewVO, ReviewDTO.class);
            reviewDTO.setNickName(nickName);
            reviewDTO.setEmail_id(email_id);//이메일 아이디 하나 추가 합니다 커스텀 마이징(삭제 조건 검사)
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


    @Override
    public void insert(ReviewDTO reviewDTO) {
        log.info("service.....DTO" + reviewDTO);
        ReviewVO reviewVO = modelMapperConfig.map(reviewDTO, ReviewVO.class);
        log.info("service......" + reviewVO);
        reviewMapper.insert(reviewVO);
    }

    @Override
    public MemberDTO setOne(String email_id) {
        MemberVO memberVO = reviewMapper.setOne(email_id);
        MemberDTO memberDTO = modelMapperConfig.map(memberVO, MemberDTO.class);

        return memberDTO;
    }

    @Override
    public int reviewScore(int facility_no, String type) {
        List<ReviewVO> reviewVOS = reviewMapper.reviewScore(facility_no, type);
        int totalScore = 0; // 총합 초기화
        int numberOfReviews = reviewVOS.size(); // 리뷰의 개수 초기화

        for (ReviewVO reviewVO : reviewVOS) {
            int score = reviewVO.getScore();
            totalScore += score; // 각 리뷰의 점수를 총합에 더함
        }

        int averageScore = (numberOfReviews > 0) ? totalScore / numberOfReviews : 0; // 평균 계산 (0으로 나누는 경우 방지)
        log.info("평균 : " + averageScore);
        return averageScore;
    }

    @Override
    public void delet(int rno) {
        reviewMapper.delet(rno);
    }

    @Override
    public void like_count(int rno) {
        reviewMapper.likeUp(rno);
    }

    @Override
    public void like_countDown(int rno) {
        reviewMapper.likeDown(rno);
    }
}
