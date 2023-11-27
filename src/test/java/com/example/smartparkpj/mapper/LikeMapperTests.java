package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.LikeVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
public class LikeMapperTests {
    @Autowired
    private LikeMapper likeMapper;

    @Test
    public void likeInTest(){
        LikeVO likeVO = LikeVO.builder()
                .rno(4)
                .mno(4)
                .build();
        likeMapper.insert(likeVO);
    }

    @Test
    public void deletTest(){
        likeMapper.delet(4, 4);
    }

    @Test
    public void LikeSetTest(){
        LikeVO likeVO = likeMapper.setOne(4, 4);
        log.info("잘 보라고 좀 : " + likeVO);
    }

    @Test
    public void LikeAllTest(){
        List<LikeVO> likeVOS = likeMapper.selectAll(3);
        log.info("라이크 올 : " + likeVOS);
    }
}
