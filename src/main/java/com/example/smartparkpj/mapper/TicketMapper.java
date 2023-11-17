package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.TicketVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TicketMapper {

    List<TicketVO> selectAll();

    TicketVO selectOne(int tno);
}
