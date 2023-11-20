package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.AttractionImageVO;
import com.example.smartparkpj.domain.AttractionTagVO;
import com.example.smartparkpj.domain.AttractionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionMapper {
    AttractionVO getOne(int facility_no);

    void addAttraction(AttractionVO attractionVO); //어트랙션 추가

    void addAttractionImage(AttractionImageVO attractionImageVO); // 이미지 추가

    void addAttractionTag(AttractionTagVO attractionTagVO); // 태그 추가

    List<AttractionImageVO> getImageList(int facility_no); // 이미지 리스트 가져오기

    List<AttractionTagVO> getTagList(int facility_no); // 태그 리스트 가져오기
}
