package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.LikeVO;
import com.example.smartparkpj.dto.LikeDTO;
import com.example.smartparkpj.dto.ReviewDTO;

import java.util.List;

public interface LikeService {
    void insert(int rno, int mno);

    void remove(int rno, int mno);

    LikeDTO setOne(int rno, int mno);

    boolean checkLiked(int rno, int mno);
}
