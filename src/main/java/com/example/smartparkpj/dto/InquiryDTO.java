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
public class InquiryDTO {
    private int ino; // 문의글 고유번호
    private String title; // 문의 제목
    private String content; // 문의 내용
    private int mno; // 문의 작성자 고유번호
    private LocalDateTime in_addDate; // 문의 작성날짜
    private String state; // 문의 답변 상태
    private String answer; // 답변 내용
    private LocalDateTime answer_addDate; // 답변 작성일
}
