package com.example.smartparkpj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacilityDTO { // 시설정보 전체 dto
    private AttractionDTO attractionDTO;
    private ShopDTO shopDTO;
    private ConvenienceDTO convenienceDTO;
    private MarkerDTO markerDTO;
}
