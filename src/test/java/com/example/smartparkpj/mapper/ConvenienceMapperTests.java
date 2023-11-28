package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.ConvenienceVO;
import com.example.smartparkpj.mapper.ConvenienceMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ConvenienceMapperTests {
    @Autowired
    private ConvenienceMapper convenienceMapper;

    @Test
    public void addAttractionTest(){     // 편의시설 추가 테스트
        ConvenienceVO convenienceVO = ConvenienceVO.builder()
                .name("편의시설1")
                .con_guide("편의시설개요")
                .con_info("편의내용")
                .con_tel("02-333-5555")
                .con_img("편의아이콘")
                .build();

        convenienceMapper.addConvenience(convenienceVO);
    }
}
