package com.example.smartparkpj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopDTO {
    private int sno; // 매장 번호
    private String shop_menu; // 매장 메뉴
    private String shop_time; // 매장 시간
    private String shop_guide; // 매장 가이드
    private String shop_price; // 매장 가격대
    private String shop_tel; // 매장 연락처
    private String shop_name; // 매장 이름

    private List<String> fileNames; // 이미지 파일이름목록

}
