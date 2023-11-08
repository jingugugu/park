package com.example.smartparkpj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {
    private int rno; // 리뷰 고유번호
    private int mno; // 멤버 고유번호
}
