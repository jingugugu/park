package com.example.smartparkpj.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShopImageVO {
    private String uuid;
    private String fileName;
    private int ord;
    private int sno;
}
