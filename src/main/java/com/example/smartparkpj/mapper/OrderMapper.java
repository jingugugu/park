package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.OrderVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    void insert(OrderVO orderVO);

}
