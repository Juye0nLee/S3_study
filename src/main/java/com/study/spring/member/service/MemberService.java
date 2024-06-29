package com.study.spring.member.service;


import com.study.spring.member.dto.MemberCreateDto;
import com.study.spring.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<CustomApiResponse<?>> signup(MemberCreateDto.Req memberCreateDto);
}
