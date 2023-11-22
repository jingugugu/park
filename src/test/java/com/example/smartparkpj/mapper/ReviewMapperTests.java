package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.ReviewVO;
import com.example.smartparkpj.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
                .build();
        List<ReviewVO> list = reviewMapper.selectLsit(pageRequestDTO);
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
                .facility_no(45)
                .type("test")
                .build();
        reviewMapper.insert(reviewVO);
    }

//    @Test
//    public void selectOne(){
//        ReviewVO reviewVO = reviewMapper.selectOne(34, "편의시설");
//        log.info("reviewVO Test selectOne : " + reviewVO);
//    }
}
