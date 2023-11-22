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
    private int fno;// 시설 넘버0
    private String facility_no;//시설 타입
    private String nickName;//조인 용 가져온 닉네임(member 값)
}
