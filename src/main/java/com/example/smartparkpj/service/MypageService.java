package com.example.smartparkpj.service;

import com.example.smartparkpj.dto.InquiryDTO;
import com.example.smartparkpj.dto.MemberDTO;
import com.example.smartparkpj.dto.OrderDTO;
import com.example.smartparkpj.dto.ReviewDTO;

import java.util.List;

public interface MypageService {
    List<OrderDTO> getMyOrder(String email_id);

    List<ReviewDTO> getMyReview(int mno);

    MemberDTO getMemberInformatrion(int mno);

    List<InquiryDTO> getMyInquiry(int mno);

}
