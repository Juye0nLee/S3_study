package com.study.spring.member.controller;


import com.study.spring.member.dto.LoginDto;
import com.study.spring.member.dto.MemberCreateDto;
import com.study.spring.member.service.MemberService;
import com.study.spring.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    //회원가입
    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> signup(@RequestBody MemberCreateDto.Req memberCreateDto){
        ResponseEntity<CustomApiResponse<?>> member = memberService.signup(memberCreateDto);
        return member;
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<?>> login(@RequestBody LoginDto.Req loginDto){
        ResponseEntity<CustomApiResponse<?>> login = memberService.login(loginDto);
        return login;
    }

}
