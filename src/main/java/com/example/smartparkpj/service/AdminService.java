package com.example.smartparkpj.service;

import com.example.smartparkpj.dto.AttractionDTO;
import com.example.smartparkpj.dto.ConvenienceDTO;
import com.example.smartparkpj.dto.MarkerDTO;
import com.example.smartparkpj.dto.ShopDTO;

public interface AdminService {

    int addAttraction(AttractionDTO attractionDTO, MarkerDTO markerDTO);

    int addShop(ShopDTO shopDTO, MarkerDTO markerDTO);

    int addConvenience(ConvenienceDTO convenienceDTO, MarkerDTO markerDTO);

    void editAttraction(AttractionDTO attractionDTO, MarkerDTO markerDTO);

    void editShop(ShopDTO shopDTO, MarkerDTO markerDTO);

    void editConvenience(ConvenienceDTO convenienceDTO, MarkerDTO markerDTO);

    void removeAttraction(AttractionDTO attractionDTO, MarkerDTO markerDTO);

    void removeShop(ShopDTO shopDTO, MarkerDTO markerDTO);

    void removeConvenience(ConvenienceDTO convenienceDTO, MarkerDTO markerDTO);
}
