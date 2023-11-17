package com.example.smartparkpj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttractionDTO {
    private int ano; // 어트랙션 고유번호
    private String aname; // 어트랙션 이름
    private String ainfo; // 어트랙션 개요
    private int passengersCount; // 탑승인원
    private String ainfo_detail; // 어트랙션 이용 정보

    private List<String> fileNames; // 이미지 파일이름목록
    private List<String> tagNames; // 태그 이름목록
}
