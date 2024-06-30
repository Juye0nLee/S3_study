package com.study.spring.domain;

import com.study.spring.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MEMBERS")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id; //멤버 테이블 기본키

    @Column(name = "MEMBER_ID")
    private String memberId; //아이디

    @Column(name = "PASSWORD")
    private String password; //비밀번호

    @Column(name = "EMAIL")
    private String email; //이메일

    @Column(name = "PHONE")
    private String phone; //전화번호

    @Column(name = "PROFILE_IMG")
    private String profileImg; //프로필 이미지 경로
}
