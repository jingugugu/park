package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.InquiryVO;
import com.example.smartparkpj.dto.InquiryDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Log4j2
public class InquiryServiceTests {

    @Autowired
    private InquiryService inquiryService;

    @Test
    public void addInquiryTests() {
        InquiryDTO inquiryDTO = InquiryDTO.builder()
                .title("제목")
                .content("내용")
                .mno(1)
                .build();
        inquiryService.add(inquiryDTO);
    }

    @Test
    public void getAllInquiryTests() {
        int mno = 2;
        inquiryService.getAll(mno);

        log.info("getAllInquiryTests");
    }

    @Test
    public void getOneInquiryTests() {
        int ino = 14;
        inquiryService.getOne(ino);

        log.info("getOneInquiryTests");
    }

    @Test
    public void testsUpdateInquiry() {
        InquiryDTO inquiryDTO = InquiryDTO.builder()
                .ino(12)
                .title("title 수정한것 ")
                .content("content수정한것")
                .build();
        inquiryService.modify(inquiryDTO);

        log.info("inquiryVO 업데이트 : " + inquiryDTO);
    }

    @Test
    public void testsDeleteInquiry() {
        int ino = 14;
        inquiryService.remove(ino);
    }

    @Test
    public void testAddAnswer() {
        InquiryDTO inquiryDTO = InquiryDTO.builder()
                .ino(12)
                .answer("답변한겁니다")
                .answer_addDate(LocalDateTime.now())
                .build();
        inquiryService.addAnswer(inquiryDTO);
    }
}
