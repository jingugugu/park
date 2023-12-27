package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.LikeVO;
import com.example.smartparkpj.domain.MemberVO;
import com.example.smartparkpj.domain.ReviewVO;
import com.example.smartparkpj.domain.TicketVO;
import com.example.smartparkpj.dto.LikeDTO;
import com.example.smartparkpj.dto.MemberDTO;
import com.example.smartparkpj.dto.TicketDTO;
import com.example.smartparkpj.mapper.LikeMapper;
import com.example.smartparkpj.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

    private final LikeMapper likeMapper;

    private final ModelMapper modelMapperConfig;

    @Override
    public void insert(int rno, int mno) {
        likeMapper.insert(rno, mno);
    }

    @Override
    public void remove(int rno, int mno) {
        likeMapper.delete(rno, mno);
    }

    @Override
    public LikeDTO setOne(int rno, int mno) {
       LikeVO likeVO = likeMapper.setOne(rno, mno);
       LikeDTO likeDTO = modelMapperConfig.map(likeVO, LikeDTO.class);

       return likeDTO;
    }


    @Override
    public boolean checkLiked(int rno, int mno) {
        if(likeMapper.selectOne(mno, rno) == 0){
            return false;
        }
        else
            return true;
    }
}
