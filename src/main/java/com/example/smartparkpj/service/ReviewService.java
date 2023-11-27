package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.MemberVO;
import com.example.smartparkpj.domain.ReviewVO;
import com.example.smartparkpj.dto.MemberDTO;
import com.example.smartparkpj.dto.PageRequestDTO;
import com.example.smartparkpj.dto.PageResponseDTO;
import com.example.smartparkpj.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    PageResponseDTO<ReviewDTO> getList(PageRequestDTO pageRequestDTO);

    void insert(ReviewDTO reviewDTO);

    MemberDTO setOne(String email_id);//리뷰 내용에 필요한 맴버 값

    int reviewScore(int facility_no, String type);//리뷰 들고 오기

    void delet(int rno);

    void like_count(int rno);

    void like_countDown(int rno);

    List<ReviewDTO> getAll();
}
