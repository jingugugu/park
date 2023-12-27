package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.LikeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeMapper {
    void insert(int rno, int mno);

    void delete(int rno, int mno);

    LikeVO setOne(int rno, int mno);

    List<LikeVO> selectAll(int mno);

    int selectOne(int mno, int rno);
}
