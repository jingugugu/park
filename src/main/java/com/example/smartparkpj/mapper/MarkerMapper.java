package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.MarkerVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MarkerMapper {
    void addMarker(MarkerVO markerVO); // 마커 등록
    List<MarkerVO> getMarkerList(); // 마커 목록
}
