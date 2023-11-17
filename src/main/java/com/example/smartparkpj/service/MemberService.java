package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.MemberVO;
import com.example.smartparkpj.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    void register(MemberDTO memberDTO);
    void addMember(MemberDTO memberDTO);
    void addMemberRole(String email_id, List<Integer> role_set);

    boolean nameCheck(String member);
}
