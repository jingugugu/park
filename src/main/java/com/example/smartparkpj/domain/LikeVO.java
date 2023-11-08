package com.example.smartparkpj.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LikeVO {
    private int rno; // 리뷰 고유번호
    private int mno; // 멤버 고유번호
}
