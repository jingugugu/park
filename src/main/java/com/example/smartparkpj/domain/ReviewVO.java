package com.example.smartparkpj.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewVO {
    private int rno; // 리뷰 고유 번호 0
    private String content; // 리뷰 내용 0
    private int mno; // 멤버 고유번호 0
    private LocalDateTime addDate; // 리뷰 작성일자 0
    private int like_count; // 좋아요 수 0
    private int score; //별점 0
    private int fno;// 시설 넘버0
    private String type;//시설 타입
}
