package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.*;
import com.example.smartparkpj.dto.PageRequestDTO;
import com.example.smartparkpj.dto.PageResponseDTO;
import com.example.smartparkpj.dto.ReviewImageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    List<ReviewVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);

    void insert(ReviewVO reviewVO);

    MemberVO setOne(String email_id);//리뷰 내용에 필요한 맴버 값

    ReviewVO score(int facility_no, String type);//별점 총점 평균

    List<ReviewVO> reviewScore(int facility_no, String type);//리뷰 들고 오기

    void delete(int rno);//리뷰 댓글 삭제 시팔 정신좀 차리고 햇깔리지마라(고지훈)

    void likeUp(int rno);

    void likeDown(int rno);

    List<ReviewVO> selectAll();// 어트렉션/시설 리뷰 동일 작성자 있는지 유효성 검사 용

    float getAvgScore(int facility_no, String type);

    void addReviewImg(ReviewImageVO reviewImageVO);
}
