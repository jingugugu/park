package com.example.smartparkpj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttractionDTO {
    private int atag_no; // 태그 고유번호
    private String atag_name; // 태그 이름
    private int ano; // 어트랙션 번호
    private String ainfo_detail; // 어트랙션 이용 정보
}
