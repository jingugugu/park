package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.ReviewVO;
import com.example.smartparkpj.dto.PageRequestDTO;
import com.example.smartparkpj.dto.PageResponseDTO;
import com.example.smartparkpj.dto.ReviewDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class ReviewServiceTests {
    @Autowired
    private ReviewService reviewService;

    @Test
    public void PageingTest(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<ReviewDTO> responseDTO = reviewService.getList(pageRequestDTO);
        log.info(responseDTO);
        responseDTO.getDtoList().stream().forEach(reviewDTO -> log.info(reviewDTO));
    }

    @Test
    public void InTest(){
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .content("test")
                .mno(3)
                .score(5)
                .facility_no(34)
                .type("편의시설")
                .build();
        log.info(reviewDTO);
        reviewService.insert(reviewDTO);
    }

    @Test
    public void ReviewRest(){
        String type = "편의시설";
        int facility_no = 34;

        int score = reviewService.reviewScore(facility_no, type);
        log.info("reviewService 평균점수 : " + score);
    }

    //--------------리뷰 좋아요 검사 시작------------------------
    @Test
    public void DeleteTest(){
        reviewService.delete(1);
    }
    @Test
    public void Like_countTest(){
        reviewService.like_count(4);
    }

    @Test
    public void Like_countDownTest(){
        reviewService.like_countDown(2);
    }
    //--------------리뷰 좋아요 검사 끝------------------------

    @Test
    public void TestSelect(){
        List<ReviewDTO> reviewDTOS = reviewService.getAll();
        log.info("리뷰 모든 목록 서비스 테스트 : " + reviewDTOS);
    }
}
