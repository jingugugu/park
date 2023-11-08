package com.example.smartparkpj.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttractionTagVO {
    private int atag_no; // 태그 고유번호
    private String atag_name; // 태그 이름
    private int ano; // 어트랙션 번호
}
