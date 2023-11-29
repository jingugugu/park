package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.MemberVO;
import com.example.smartparkpj.domain.ReviewImageVO;
import com.example.smartparkpj.domain.ReviewVO;
import com.example.smartparkpj.dto.*;
import com.example.smartparkpj.mapper.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;

    private final ModelMapper modelMapperConfig;

    private final MemberMapper memberMapper;

    private final AttractionMapper attractionMapper;

    private final ShopMapper shopMapper;

    private final ConvenienceMapper convenienceMapper;


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
        float avgScore = reviewMapper.getAvgScore(reviewDTO.getFacility_no(),reviewDTO.getType());
        log.info("평균 점수 : " + avgScore);
        if(reviewDTO.getType().equals("어트랙션")){
            attractionMapper.updateScore(reviewDTO.getFacility_no(),avgScore);
        }
        else if(reviewDTO.getType().equals("매장")){
            shopMapper.updateScore(reviewDTO.getFacility_no(),avgScore);
        }
        else if(reviewDTO.getType().equals("편의시설")){
            convenienceMapper.updateScore(reviewDTO.getFacility_no(),avgScore);
        }
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
    public void delete(int rno) {
        reviewMapper.delete(rno);
    }

    @Override
    public void like_count(int rno) {
        reviewMapper.likeUp(rno);
    }

    @Override
    public void like_countDown(int rno) {
        reviewMapper.likeDown(rno);
    }

    @Override
    public List<ReviewDTO> getAll() {
        List<ReviewVO> voLsit = reviewMapper.selectAll();
        List<ReviewDTO> dtoList = new ArrayList<>();
        for(ReviewVO reviewVO : voLsit){
            ReviewDTO reviewDTO = modelMapperConfig.map(reviewVO, ReviewDTO.class);
            dtoList.add(reviewDTO);
        }
        return dtoList;
    }

    @Override
    public void reviewImageIn(ReviewImageDTO reviewImageDTO) {
        log.info("service.....DTO" + reviewImageDTO);
        String uuid = UUID.randomUUID().toString();
        reviewImageDTO.setUuid(uuid);
        ReviewImageVO reviewImageVO = modelMapperConfig.map(reviewImageDTO, ReviewImageVO.class);
        log.info("service......" + reviewImageVO);
//        reviewMapper.addReviewImage(reviewImageVO);
    }
}
