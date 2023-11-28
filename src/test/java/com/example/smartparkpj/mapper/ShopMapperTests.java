package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.AttractionImageVO;
import com.example.smartparkpj.domain.AttractionVO;
import com.example.smartparkpj.domain.ShopImageVO;
import com.example.smartparkpj.domain.ShopVO;
import com.example.smartparkpj.mapper.ShopMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
@Log4j2
public class ShopMapperTests {
    @Autowired
    private ShopMapper shopMapper;

    @Test
    public void addShopImageTest(){     // 이미지 추가 테스트
        ShopVO shopVO = ShopVO.builder()
                .shop_menu("메뉴1, 메뉴2, 메뉴3")
                .shop_name("매장1")
                .shop_guide("라스트오더 어쩌구")
                .shop_tel("02-112-4444")
                .shop_time("평일됨")
                .shop_price("6천원~10만원")
                .build();
        shopMapper.addShop(shopVO);

        for(int i = 1; i <= 3 ; i++){
            ShopImageVO shopImageVO = ShopImageVO.builder()
                    .uuid(UUID.randomUUID().toString())
                    .fileName("이미지" + i)
                    .ord(i)
                    .sno(shopVO.getSno())
                    .build();

            shopMapper.addShopImage(shopImageVO);
        }
    }
}
