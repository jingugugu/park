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
    private int rno; // 리뷰 고유 번호
    private String title; // 리뷰 제목
    private String content; // 리뷰 내용
    private int mno; // 멤버 고유번호
    private LocalDateTime addDate; // 리뷰 작성일자
    private int like_count; // 좋아요 수
    private int ono; // 예매 고유 번호
    private int view_count; // 조회수
    private String rPhoto; // 리뷰 포토
}
