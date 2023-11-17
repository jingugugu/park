package com.example.smartparkpj.service;

import com.example.smartparkpj.domain.MemberRole;
import com.example.smartparkpj.domain.MemberVO;
import com.example.smartparkpj.dto.MemberDTO;
import com.example.smartparkpj.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final ModelMapper modelMapper;
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void register(MemberDTO memberDTO) {
        log.info("memberDTO" + memberDTO);

        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);

        memberMapper.register(memberVO);
    }

    @Override
    public void addMember(MemberDTO memberDTO) {
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        memberVO.changePassword(passwordEncoder.encode(memberDTO.getPassword())); // 패스워드를 암호화 해서 저장
        memberVO.addRole(MemberRole.USER); // 멤버 user 권한 부여
        log.info("============================================");
        log.info(memberVO);
        log.info(memberVO.getRoleSet());
        memberMapper.addMember(memberVO);
        List<Integer> role_set = new ArrayList<>();
        role_set.add(0);
        addMemberRole(memberDTO.getEmail_id(), role_set);
    }

    @Override
    public void addMemberRole(String email_id, List<Integer> role_set) {
        for(int i=0; i < role_set.size(); i++) {
            memberMapper.addMemberRole(email_id, role_set.get(i));
        }
    }

    @Override
    public boolean nameCheck(String member) {
        if(memberMapper.nameCheck(member) >= 1){
            return true;
        } else{
            return false;
        }
    }


}
