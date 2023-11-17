package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    void register(MemberVO memberVO);
    void addMember(MemberVO memberVO);
    void addMemberRole(String member_mid, Integer role_set);

    int nameCheck(String member);

}
