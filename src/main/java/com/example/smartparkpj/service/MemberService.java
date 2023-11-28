package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.MemberVO;
import com.example.smartparkpj.dto.MemberDTO;

import java.lang.reflect.Member;
import java.util.List;

public interface MemberService {
    void register(MemberDTO memberDTO);
    void addMember(MemberDTO memberDTO);
    void addMemberRole(String email_id, List<Integer> role_set);
    boolean emailCheck(String email_id);
    boolean nickCheck(String nickName);
    // 수정페이지 노출시 나와야 할 정보
    MemberDTO getMember(String email_id);
    void edit(MemberDTO memberDTO);
    void deleteReason(MemberDTO memberDTO);
    void editPassword(MemberDTO memberDTO);
    List<MemberDTO> selectAll();
    MemberDTO selectOne(int mno);
    void removeMember(MemberDTO memberDTO);

}
