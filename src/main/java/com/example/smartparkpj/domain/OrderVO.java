package com.example.smartparkpj.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderVO { 
    private int ono; // 예매 고유번호
    private String orderCode; // 예매코드
    private int tno; // 티켓 고유번호
    private String email_id; // 구매자 이메일 아이디
    private Date orderDate; // 예매일
    private LocalDateTime startDate; // 방문가능일자(from)
    private LocalDateTime endDate; // 방문가능일자(to)
    private boolean finished; // 만료여부
    private int people_count; // 인원수
    private boolean has_ability; // 리뷰 작성 가능 여부
    private int price;//총가격

}
