package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.InquiryVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class InquiryMapperTests {

    @Autowired
    private InquiryMapper inquiryMapper;


    @Test
    public void testInsert() {
        InquiryVO inquiryVO = InquiryVO.builder()
                .title("제목")
                .content("내용")
                .mno(1)
                .build();
        inquiryMapper.addInquiry(inquiryVO);

        log.info(inquiryMapper);
    }
}
