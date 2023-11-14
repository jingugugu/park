package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.AttractionImageVO;
import com.example.smartparkpj.domain.AttractionTagVO;
import com.example.smartparkpj.domain.AttractionVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttractionMapper {
    AttractionVO getOne(int facility_no);

    void addAttraction(AttractionVO attractionVO); //어트랙션 추가

    void addAttractionImage(AttractionImageVO attractionImageVO); // 이미지 추가

    void addAttractionTag(AttractionTagVO attractionTagVO); // 태그 추가
}
