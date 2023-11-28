package com.example.smartparkpj.mapper;

import com.example.smartparkpj.domain.InquiryVO;
import com.example.smartparkpj.domain.MemberRole;
import com.example.smartparkpj.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    void register(MemberVO memberVO);
    void addMember(MemberVO memberVO);
    void addMemberRole(String member_mid, Integer role_set);
    int emailCheck(String email_id);
    int nickCheck(String nickName);
    MemberVO selectMember(String email_id);
    void update(MemberVO memberVO);
    MemberVO selectOne(int mno); //리뷰에 닉네임 들고 오기위해 하나 만듭니다 (고지훈)
    List<Integer> selectRoles(String member);
    void deleteReason(MemberVO memberVO);
    void passwordEdit(MemberVO memberVO);
    List<MemberVO> selectAll();
    void removeMember(MemberVO memberVO);

}
