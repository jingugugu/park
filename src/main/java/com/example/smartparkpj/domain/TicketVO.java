package com.example.smartparkpj.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TicketVO {
    private int tno; // 티켓 고유 번호
    private String tname; // 티켓 이름
    private String tinfo; // 티켓 정보
    private int tprice; // 티켓 가격
}
