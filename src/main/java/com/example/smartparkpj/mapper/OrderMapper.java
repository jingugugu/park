package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    void insert(OrderVO orderVO);

    List<OrderVO> selectOneAll(String email_id);

    void updateFinished(String orderVO);

    void updateHasAbility(String orderVO);
}
