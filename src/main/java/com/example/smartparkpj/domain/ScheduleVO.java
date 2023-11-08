package com.example.smartparkpj.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScheduleVO {
    private int schedule_no; // 운영일정 고유번호
    private LocalDate openDate; // 날짜
    private boolean statusOpen; // 운영 여부
}
