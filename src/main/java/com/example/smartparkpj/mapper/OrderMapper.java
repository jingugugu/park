package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    void insert(OrderVO orderVO);

    List<OrderVO> selectOneAll(String email_id);//마이페이지 에서 활용 가능

    OrderVO selectOne(int ono);

    OrderVO selectOneNow(String email_id);

    void updateFinished(int ono);

    void updateHasAbility(int ono);
}
