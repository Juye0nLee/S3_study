package com.study.spring.post.service;

import com.study.spring.domain.Post;
import com.study.spring.post.dto.PostCreateDto;
import com.study.spring.post.repository.PostRepository;
import com.study.spring.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public ResponseEntity<CustomApiResponse<?>> createPost(PostCreateDto.Req postCreateDto) {
        Post post = postCreateDto.toEntity();
        Post savedPost = postRepository.save(post);

        CustomApiResponse<?> res = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null,"게시글이 작성되었습니다.");
        return ResponseEntity.ok(res);
    }
}
