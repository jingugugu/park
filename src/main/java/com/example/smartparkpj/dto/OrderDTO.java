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
public class OrderDTO {
    private int ono; // 예매 고유번호
    private String orderCode; // 예매코드
    private int tno; // 티켓 고유번호
    private String email_id; // 구매자 이메일 아이디
    private LocalDateTime orderDate; // 예매일
    private String startDate; // 방문가능일자(from)
    private String endDate; // 방문가능일자(to)
    private int finished; // 만료여부
    private int people_count; // 인원수
    private int has_ability; // 리뷰 작성 가능 여부
    private int price;//총가격
    private String tname; // 티켓 이름
}
