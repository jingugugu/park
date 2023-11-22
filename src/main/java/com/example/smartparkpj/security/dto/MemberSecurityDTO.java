package com.example.smartparkpj.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;

@Log4j2
@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User {


    private int mno; // 멤버 고유번호
    private String email_id; // 멤버 이메일아이디
    private String password; // 멤버 비밀번호
    private String nickName; // 멤버 닉네임
    private String member_name; // 멤버 이름
    private String phone; // 멤버 연락처
    private LocalDateTime addDate; // 멤버 가입일
    private String birthday; // 멤버 생년월일
    private String profileImg; // 멤버 프로필 이미지
    private boolean del; // 탈퇴 여부
    private boolean social; // 소셜 로그인 자동 회원 가입 여부


    // 수정이 필요한 값은 필수로 들어갸아함. 일단 뭘 수정하고 할지 잘 모르니 다 넣어봄
    public MemberSecurityDTO(int mno, String username, String password, String nickName,
                             String member_name, String phone, String birthday, String profileImg,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.mno = mno;
        this.email_id = username;
        this.password = password;
        this.nickName = nickName;
        this.member_name = member_name;
        this.phone = phone;
        this.birthday = birthday;
        this.profileImg = profileImg;
    }
}
