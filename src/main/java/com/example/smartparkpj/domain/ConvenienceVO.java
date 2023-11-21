package com.example.smartparkpj.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConvenienceVO {
    private int cno; // 편의시설 고유번호
    private String name; // 편의시설 이름
    private String con_info; // 편의시설 개요
    private String con_guide; // 편의시설 이용기준
    private String con_tel; // 편의시설 연락처
    private String con_img; // 편의시설 아이콘
    private float avgScore; // 평점

}
