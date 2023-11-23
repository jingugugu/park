package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.InquiryVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
public class InquiryMapperTests {

    @Autowired
    private InquiryMapper inquiryMapper;

    @Test
    public void testInsert() {
        InquiryVO inquiryVO = InquiryVO.builder()
                .title("나는 3번 타이틀")
                .content("3번내용이지")
                .mno(1)
                .build();
        inquiryMapper.addInquiry(inquiryVO);

        log.info(inquiryMapper);
    }

    @Test
    public void testSelectAll() {
        int mno = 1;
        List<InquiryVO> inquiryVOS = inquiryMapper.selectAll(mno);

        log.info("testSelectAll-------------------------------");
        log.info(inquiryVOS);
    }

    @Test
    public void testSelectOne() {
        int ino = 14;
        InquiryVO inquiryVO = inquiryMapper.selectOne(ino);

        log.info("testSelectOne-------------------------------");
        log.info(inquiryVO);

    }

    @Test
    public void testUpdateAnswer() {
        InquiryVO inquiryVO = InquiryVO.builder()
                .ino(35)
                .answer("35번 테스트코드")
                .answer_addDate(LocalDateTime.now())
                .build();
        inquiryMapper.adminAnswer(inquiryVO);

        log.info(inquiryVO);
    }

    @Test
    public void adminInquiryListTests() {
        List<InquiryVO> inquiryVOS = inquiryMapper.adminInquiryList();
        log.info(inquiryVOS);
    }

    @Test
    public void adminInquriryDeleteTest() {
        int ino = 28;
        inquiryMapper.adminInquiryDelete(ino);
    }

}
