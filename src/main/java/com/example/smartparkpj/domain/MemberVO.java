package com.example.smartparkpj.domain;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberVO {

    private int mno; // 멤버 고유번호
    @Id
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

    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY) // Entity 가 아닌 단순한 형태의 객체 집합을 정의하고 관리
    private Set<MemberRole> roleSet = new HashSet<>();

    public void changeEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeNickName(String nickName) {
        this.nickName = nickName;
    }

    public void changeMember_name(String member_name) {
        this.member_name = member_name;
    }

    public void changePhone(String phone) {
        this.phone = phone;
    }

    public void changeBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void changeDel(boolean del) {
        this.del = del;
    }

    public void changeSocial(boolean social) {
        this.social = social;
    }

    public void addRole(MemberRole memberRole) {
        this.roleSet.add(memberRole);
    }

    public void setRoleSet(Set<MemberRole> roleSet) {
        this.roleSet = roleSet;
    }

    public void clearRole() {
        this.roleSet.clear();
    }

}
