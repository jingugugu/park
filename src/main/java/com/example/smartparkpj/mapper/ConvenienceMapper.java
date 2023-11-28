package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.ConvenienceVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConvenienceMapper {

    void addConvenience(ConvenienceVO convenienceVO); // 편의시설 추가

    ConvenienceVO getOne(int facility_no);

    void editConvenience(ConvenienceVO convenienceVO);

    void removeConvenience(int cno);

    void updateScore(int cno, float avgScore);
}
