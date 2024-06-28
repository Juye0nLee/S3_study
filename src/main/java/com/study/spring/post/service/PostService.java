package com.study.spring.post.service;

import com.study.spring.post.dto.PostCreateDto;
import com.study.spring.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface PostService {
    ResponseEntity<CustomApiResponse<?>> createPost(PostCreateDto.Req postCreateDto);
}
