package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.ReviewVO;
import com.example.smartparkpj.dto.*;

import java.util.List;

public interface ReviewService {
    PageResponseDTO<ReviewDTO> getList(PageRequestDTO pageRequestDTO, int mno);

    void insert(ReviewDTO reviewDTO);

    int reviewScore(int facility_no, String type);//리뷰 들고 오기

    ReviewDTO delete(int rno);

    void like_count(int rno);

    void like_countDown(int rno);

    List<ReviewDTO> getAll();

    List<OrderDTO> getAvailableOrderList(int mno, String type, int facility_no); // 리뷰 작성 가능한 티켓 리스트

    void reviewImageIn(ReviewImageDTO reviewImageDTO);
}
