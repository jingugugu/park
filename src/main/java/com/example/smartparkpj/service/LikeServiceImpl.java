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
    public void insert(LikeDTO likeDTO) {
        LikeVO likeVO = modelMapperConfig.map(likeDTO, LikeVO.class);
        likeMapper.insert(likeVO);
    }

    @Override
    public void remove(int rno, int mno) {
        likeMapper.delet(rno, mno);
    }

    @Override
    public LikeDTO setOne(int rno, int mno) {
       LikeVO likeVO = likeMapper.setOne(rno, mno);
       LikeDTO likeDTO = modelMapperConfig.map(likeVO, LikeDTO.class);

       return likeDTO;
    }

    @Override
    public List<LikeDTO> selectAll(int mno) {
        List<LikeVO> voLsit = likeMapper.selectAll(mno);
        List<LikeDTO> dtoList = new ArrayList<>();
        for(LikeVO likeVO : voLsit){
            LikeDTO likeDTO = modelMapperConfig.map(likeVO, LikeDTO.class);
            dtoList.add(likeDTO);
        }
        return dtoList;
    }
}
