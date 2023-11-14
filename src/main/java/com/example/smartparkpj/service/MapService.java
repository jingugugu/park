package com.example.smartparkpj.service;

import com.example.smartparkpj.dto.FacilityDTO;
import com.example.smartparkpj.dto.MarkerDTO;

import java.util.List;

public interface MapService {
    int add(FacilityDTO facilityDTO);

    List<MarkerDTO> getMarkerList();

    Object getMarkerOne(String type, int facility_no);

}
