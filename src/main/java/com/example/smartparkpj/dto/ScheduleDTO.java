package com.example.smartparkpj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
    private int schedule_no; // 운영일정 고유번호
    private LocalDate openDate; // 날짜
    private boolean statusOpen; // 운영 여부
}
