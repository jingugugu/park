package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.LikeVO;
import com.example.smartparkpj.domain.ReviewVO;
import com.example.smartparkpj.domain.TicketVO;
import com.example.smartparkpj.dto.LikeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeMapper {
    void insert(LikeVO likeVO);

    void delet(int rno, int mno);

    LikeVO setOne(int rno, int mno);

    List<LikeVO> selectAll(int mno);
}
