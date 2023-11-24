package com.example.smartparkpj.service;

import com.example.smartparkpj.dto.LikeDTO;
import com.example.smartparkpj.dto.ReviewDTO;

public interface LikeService {
    void insert(LikeDTO likeDTO);

    void remove(int rno, int mno);

    LikeDTO setOne(int rno, int mno);
}
