package com.example.smartparkpj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private int mno; // 멤버 고유번호
    private String email_id; // 멤버 이메일아이디
    private String password; // 멤버 비밀번호
    private String nickName; // 멤버 닉네임
    private String member_name; // 멤버 이름
    private String phone; // 멤버 연락처
    private LocalDateTime addDate; // 멤버 가입일
    private LocalDateTime requestDate; // 탈퇴 신청일
    private LocalDateTime removeDate; // 탈퇴 처리 날짜
    private String birthday; // 멤버 생년월일
    private String profileImg; // 멤버 프로필 이미지
    private boolean del; // 탈퇴 여부
    private boolean social; // 소셜 로그인 자동 회원 가입 여부
}
