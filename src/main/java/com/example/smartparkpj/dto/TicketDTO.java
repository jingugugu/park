package com.example.smartparkpj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private int tno; // 티켓 고유 번호
    private String tname; // 티켓 이름
    private String tinfo; // 티켓 정보
    private int tprice; // 티켓 가격
}
