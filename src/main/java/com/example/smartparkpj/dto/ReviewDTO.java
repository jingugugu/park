package com.example.smartparkpj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private int rno; // 리뷰 고유 번호 0
    private String content; // 리뷰 내용 0
    private int mno; // 멤버 고유번호 0
    private LocalDateTime addDate; // 리뷰 작성일자 0
    private int like_count; // 좋아요 수 0
    private int score; //별점 0
    private int facility_no;// 시설 넘버0
    private String type;//시설 타입
    private int ono;//티켓 구매 넘버
    private String nickName;//조인 용 가져온 닉네임(member 값)
    private String email_id;//이메일 아이디 하나 추가 합니다(삭제 조건 값)
}
