package com.example.smartparkpj.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MarkerVO {
    private int marker_no; // 마커 고유번호
    private double latitude; // 위도
    private double longitude; // 경도
    private String type; // 시설 타입
    private int facility_no; // 시설 번호
    private String marker_img; // 마커 아이콘 이미지
}
