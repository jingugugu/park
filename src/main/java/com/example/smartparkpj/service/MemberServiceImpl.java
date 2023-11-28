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

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public boolean emailCheck(String email_id) {
        if(memberMapper.emailCheck(email_id) >= 1) {
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean nickCheck(String nickName) {
        if (memberMapper.nickCheck(nickName) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public MemberDTO getMember(String email_id) {
        log.info("==========getMember=========");
        log.info(email_id);

        MemberDTO memberDTO = modelMapper.map(memberMapper.selectMember(email_id), MemberDTO.class);
        log.info(memberDTO);
        return memberDTO;
    }

    @Override
    public void edit(MemberDTO memberDTO) {
        log.info("==========edit=========");
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        memberMapper.update(memberVO);
    }

    @Override
    public void deleteReason(MemberDTO memberDTO) {
        log.info("====================deleteReason===================");
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        memberMapper.deleteReason(memberVO);
    }

    @Override
    public void editPassword(MemberDTO memberDTO) {
        log.info("====================editPassword===================");
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        memberVO.changePassword(passwordEncoder.encode(memberDTO.getPassword()));
        memberMapper.passwordEdit(memberVO);
    }

    @Override
    public List<MemberDTO> selectAll() {
        List<MemberVO> memberVOList = memberMapper.selectAll();
        log.info("memberVOList = " + memberVOList);
//        List<MemberDTO> memberDTOList = memberVOList.stream().map(vo -> modelMapper.map(vo, MemberDTO.class)).collect(Collectors.toList());
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberVO memberVO : memberVOList) {
            log.info("memberVO = " + memberVO);
            MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
            memberDTOList.add(memberDTO);
        }
        log.info("====================selectAll===================");
        log.info(memberDTOList.size());
        return memberDTOList;
    }

    @Override
    public MemberDTO selectOne(int mno) {
        log.info("====================selectOne===================" + mno);
        MemberVO memberVO = memberMapper.selectOne(mno);
        log.info("====================selectOne===================" + memberVO);
        MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
        log.info("====================selectOne===================" + memberDTO);
        return memberDTO;
    }

    @Override
    public void removeMember(MemberDTO memberDTO) {
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        log.info("====================removeMember===================");
        memberMapper.removeMember(memberVO);
    }

}
