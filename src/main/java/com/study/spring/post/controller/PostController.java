package com.study.spring.post.controller;


import com.study.spring.domain.Post;
import com.study.spring.post.dto.PostCreateDto;
import com.study.spring.post.service.PostService;
import com.study.spring.util.response.CustomApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    //게시글 작성
    @PostMapping
    public ResponseEntity<CustomApiResponse<?>> createPost(@RequestBody PostCreateDto.Req postCreateDto) {
        ResponseEntity<CustomApiResponse<?>> post = postService.createPost(postCreateDto);
        return post;
    }

    //게시글 조회
    @GetMapping
    public ResponseEntity<CustomApiResponse<?>> getPost(@RequestParam("postId") Long postId) {
        ResponseEntity<CustomApiResponse<?>> post = postService.getPost(postId);
        return post;
    }



}
