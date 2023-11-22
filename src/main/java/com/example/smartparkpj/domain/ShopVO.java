package com.example.smartparkpj.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShopVO {
    private int sno; // 매장 번호
    private String shop_menu; // 매장 메뉴
    private String shop_time; // 매장 시간
    private String shop_guide; // 매장 가이드
    private String shop_price; // 매장 가격대
    private String shop_tel; // 매장 연락처
    private String shop_name; // 매장 이름
    private float avgScore; // 평점
}
