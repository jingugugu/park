package com.example.smartparkpj.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttractionVO {
    private int ano; // 어트랙션 고유번호
    private String aname; // 어트랙션 이름
    private String ainfo; // 어트랙션 개요
    private int passengersCount; // 탑승인원
    private String ainfo_detail; // 어트랙션 이용 정보

    private String attraction_thumnail; // 어트랙션 이미지
}
