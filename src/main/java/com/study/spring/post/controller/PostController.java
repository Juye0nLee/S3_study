package com.study.spring.post.controller;


import com.study.spring.domain.Post;
import com.study.spring.post.dto.PostCreateDto;
import com.study.spring.post.service.PostService;
import com.study.spring.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> createPost(@RequestBody PostCreateDto.Req postCreateDto) {
        ResponseEntity<CustomApiResponse<?>> post = postService.createPost(postCreateDto);
        return post;
    }



}
