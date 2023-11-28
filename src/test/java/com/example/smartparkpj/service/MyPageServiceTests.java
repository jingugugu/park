package com.example.smartparkpj.service;

import com.example.smartparkpj.dto.OrderDTO;
import com.example.smartparkpj.mapper.MarkerMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class MyPageServiceTests {
    @Autowired
    private MypageService mypageService;

    @Test
    public void getMyOrderListTests() {
        String email_id = "othosw@naver.com";
        mypageService.getMyOrder(email_id);
        log.info(mypageService);
    }

    @Test
    public void getMyReviewListTests() {
        int mno = 4;
        mypageService.getMyReview(mno);
        log.info(mypageService);
    }

    @Test
    public void getMemberInformationTests() {
        int mno = 2;
        mypageService.getMemberInformatrion(mno);
        log.info(mypageService);
    }

    @Test
    public void getMyInquiryListTests() {
        int mno = 4;
        mypageService.getMyInquiry(mno);
        log.info(mypageService);
    }
}
