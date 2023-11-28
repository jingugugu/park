package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.InquiryVO;
import com.example.smartparkpj.domain.MemberVO;
import com.example.smartparkpj.domain.OrderVO;
import com.example.smartparkpj.domain.ReviewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {

    List<OrderVO> myOrder (String email_id);

    List<ReviewVO> myReview (int mno);

    MemberVO myPageInformation(int mno);

    List<InquiryVO> myPageInquiry (int mno);

}
