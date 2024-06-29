package com.study.spring.member.service;


import com.study.spring.domain.Member;
import com.study.spring.member.dto.MemberCreateDto;
import com.study.spring.member.repository.MemberRepository;
import com.study.spring.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    public ResponseEntity<CustomApiResponse<?>> signup(MemberCreateDto.Req memberCreateDto) {
        //해당 유저가 이미 존재하는지 확인
        String name = memberCreateDto.getName();
        Optional<Member> findMember = memberRepository.findByName(name);
        if (findMember.isPresent()) {

            CustomApiResponse<?> failResponse = CustomApiResponse.createFailWithout(HttpStatus.BAD_REQUEST.value(), "이미 존재하는 회원입니다.");
            return new ResponseEntity<>(failResponse, HttpStatus.BAD_REQUEST);
        }
        //새로운 유저라면 회원가입
        //newMember 객체 생성 후 값 할당
        Member newMember = Member.builder()
                .name(name)
                .password(memberCreateDto.getPassword())
                .email(memberCreateDto.getEmail())
                .phone(memberCreateDto.getPhone())
                .profileImg(memberCreateDto.getProfileImg())
                .build();
        //db에 회원 저장
        memberRepository.save(newMember);

        CustomApiResponse res = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null,"회원가입에 성공하였습니다.");
        return ResponseEntity.ok(res);

    }
}
