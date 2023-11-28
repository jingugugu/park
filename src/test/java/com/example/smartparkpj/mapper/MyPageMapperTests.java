package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.InquiryVO;
import com.example.smartparkpj.domain.MemberVO;
import com.example.smartparkpj.domain.OrderVO;
import com.example.smartparkpj.domain.ReviewVO;
import com.example.smartparkpj.mapper.MyPageMapper;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Log4j2
public class MyPageMapperTests {

    @Autowired
    private MyPageMapper myPageMapper;

    @Test
    public void testSelectOrder() {
        String email_id = "othosw@naver.com";
        List<OrderVO> orderVOList = myPageMapper.myOrder(email_id);
        log.info(orderVOList);

    }


    @Test
    public void testSelectReview() {
        int mno = 4;
        List<ReviewVO> reviewVOS = myPageMapper.myReview(mno);
        log.info("--------------------리뷰 리스트--------------------------");
        log.info(reviewVOS);
    }

    @Test
    public void testSelectMember() {
        int mno = 3;
        log.info("===============================================================");
        MemberVO memberVO = myPageMapper.myPageInformation(mno);
        log.info(memberVO);
    }

    @Test
    public void testSelectInquiry() {
        int mno = 4;
        List<InquiryVO> inquiryVOS = myPageMapper.myPageInquiry(mno);
        log.info("--------------------문의 리스트--------------------------");
        log.info(inquiryVOS);
    }

}
