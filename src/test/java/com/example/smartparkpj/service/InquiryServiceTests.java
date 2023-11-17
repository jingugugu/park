package com.example.smartparkpj.service;

import com.example.smartparkpj.dto.InquiryDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
                .state("ㅎㅇ")
                .build();
        inquiryService.add(inquiryDTO);
    }
}
