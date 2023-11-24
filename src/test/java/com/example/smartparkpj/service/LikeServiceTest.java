package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.LikeVO;
import com.example.smartparkpj.dto.LikeDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class LikeServiceTest {
    @Autowired
    private LikeService likeService;

    @Test
    public void likeTest(){
        LikeDTO likeDTO = LikeDTO.builder()
                .mno(4)
                .rno(4)
                .build();
        likeService.insert(likeDTO);
    }

    @Test
    public void likeDeletTest(){
        LikeDTO likeDTO = likeService.setOne(4, 4);
        log.info("서브스 라이크 검사 : " + likeDTO);
    }
}
