package com.example.smartparkpj.service;

import com.example.smartparkpj.dto.PageRequestDTO;
import com.example.smartparkpj.dto.PageResponseDTO;
import com.example.smartparkpj.dto.ReviewDTO;

public interface ReviewService {
    PageResponseDTO<ReviewDTO> getList(PageRequestDTO pageRequestDTO);
}
