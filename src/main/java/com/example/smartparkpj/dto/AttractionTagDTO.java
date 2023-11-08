package com.example.smartparkpj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttractionTagDTO {
    private int ano; // 어트랙션 고유번호
    private String aname; // 어트랙션 이름
    private String ainfo; // 어트랙션 개요
    private int passengersCount; // 탑승인원
    private String attraction_img; // 어트랙션 이미지
}
