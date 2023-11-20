package com.example.smartparkpj.service;

import com.example.smartparkpj.dto.AttractionDTO;
import com.example.smartparkpj.dto.MarkerDTO;
import com.example.smartparkpj.dto.ShopDTO;

public interface AdminService {

    void addAttraction(AttractionDTO attractionDTO, MarkerDTO markerDTO);

    void addShop(ShopDTO shopDTO, MarkerDTO markerDTO);
}
