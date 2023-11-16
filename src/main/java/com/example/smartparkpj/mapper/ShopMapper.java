package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.ShopImageVO;
import com.example.smartparkpj.domain.ShopVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopMapper {

    void addShop(ShopVO shopVO); // 매장 추가

    void addShopImage(ShopImageVO shopImageVO); // 매장 이미지 추가

    ShopVO getOne(int facility_no);
}