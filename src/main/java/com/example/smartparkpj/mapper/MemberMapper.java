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
    int nickCheck(String nickName);
    boolean login(String email_id, String password);
    MemberVO selectMember(String email_id);

    // 로그인 (UserDetails 유저 정보 생성) -> 마이페이지 -> 정보수정 (페이지) -> 컨트롤러에서 실제로 정보수정
}
