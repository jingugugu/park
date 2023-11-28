package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.*;
import com.example.smartparkpj.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ReviewMapperTests {
    @Autowired
    private ReviewMapper reviewMapper;

    @Test
    public void selectLsitTest(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(11)
                .type("어트렉션")
                .facility_no(47)
                .build();
        List<ReviewVO> list = reviewMapper.selectList(pageRequestDTO);
        for(ReviewVO reviewVO : list){
            log.info("reviewVOList : " + reviewVO);
        }
    }

    @Test
    public void getCount(){
        reviewMapper.getCount(PageRequestDTO.builder().build());
    }

    @Test
    public void insertTest(){
        ReviewVO reviewVO = ReviewVO.builder() //content, mno, addDate, score, fno, type
                .content("test")
                .mno(4)
                .score(5)
                .facility_no(34)
                .type("편의시설")
                .build();
        reviewMapper.insert(reviewVO);
    }

    @Test
    public void setOneTest(){
        String id = "gojihoon12313@naver.com";
        MemberVO memberVO = reviewMapper.setOne(id);

        log.info("mno값을 위하여 : " + memberVO);
    }

    @Test
    public void reviewTest(){
        List<ReviewVO> list = reviewMapper.reviewScore(34, "편의시설");
        for(ReviewVO reviewVO : list){
            log.info("reviewList + 별점" + reviewVO.getScore());
        }
    }

    //--------------좋아요 기능 테스트 목록 시작-------------------------
    @Test
    public void deletTest(){
        reviewMapper.delet(18);
    }

    @Test
    public void LikeTest(){
        reviewMapper.likeUp(3);
    }

    @Test
    public void LikeDown(){
        reviewMapper.likeDown(2);
    }
    //--------------좋아요 기능 테스트 목록 끝-------------------------

    @Test
    public void listTest(){
        List<ReviewVO> reviewVOS = reviewMapper.selectAll();
        log.info("리뷰 전체 목록 : " + reviewVOS);
    }

    //------------------이미지 추가 테스트--------------------
    @Test
    public void addAttractionImageTest(){     // 이미지 추가 테스트
        ReviewImageVO reviewImageVO = ReviewImageVO.builder()
                .uuid(UUID.randomUUID().toString())
                .fileName("이미지" + 1)
                .ord(1)
                .rno(1)
                .build();
        reviewMapper.addReviewImage(reviewImageVO);
    }
}
