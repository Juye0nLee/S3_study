package com.study.spring.post.service;

import com.study.spring.domain.Member;
import com.study.spring.domain.Post;
import com.study.spring.member.repository.MemberRepository;
import com.study.spring.post.dto.PostCreateDto;
import com.study.spring.post.repository.PostRepository;
import com.study.spring.util.response.CustomApiResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Builder
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    //게시물 작성
    @Override
    public ResponseEntity<CustomApiResponse<?>> createPost(PostCreateDto.Req postCreateDto) {
        Post post = postCreateDto.toEntity();
        postRepository.save(post);

        CustomApiResponse<?> res = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null,"게시글이 작성되었습니다.");
        return ResponseEntity.ok(res);
    }

    //게시물 조회
    @Override
    public ResponseEntity<CustomApiResponse<?>> getPost(Long postId) {
        //해당 게시물이 존재하는 게시물인지 확인
        Optional<Post> findPost = postRepository.findById(postId);
        //존재하지 않는 게시물일 때
        if (findPost.isEmpty()) {
            CustomApiResponse<?> failResponse = CustomApiResponse.createFailWithout(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 게시물 입니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(failResponse);
        }

        //게시물 작성자 이름 찾기
        Optional<Member> findMember = memberRepository.findById(findPost.get().getMember().getId());

        Post post = findPost.get();

        //응답할 때 제목, 내용, 이미지 , 작성자를 줘야함



        return null;
    }


}
