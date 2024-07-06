package com.study.spring.post.service;

import com.amazonaws.services.s3.AmazonS3;
import com.study.spring.domain.Member;
import com.study.spring.domain.Post;
import com.study.spring.member.repository.MemberRepository;
import com.study.spring.post.dto.PostCreateDto;
import com.study.spring.post.dto.PostListDto;
import com.study.spring.post.repository.PostRepository;
import com.study.spring.util.entity.ImageEntity;
import com.study.spring.util.repository.ImageRepository;
import com.study.spring.util.response.CustomApiResponse;
import com.study.spring.util.service.S3UploadService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;

    private final S3UploadService s3UploadService;
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    //게시물 작성
    @Override
    public ResponseEntity<CustomApiResponse<?>> createPost(PostCreateDto.Req postCreateDto) {
        try{
            //게시물 작성자가 존재하는지 확인
            Optional<Member> findMember = memberRepository.findById(postCreateDto.getId());
            if (findMember.isEmpty()) {
                CustomApiResponse<?> failResponse = CustomApiResponse.createFailWithout(HttpStatus.NOT_FOUND.value(), "해당 회원은 존재하지 않는 회원입니다.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(failResponse);
            }

            Post post = Post.builder()
                    .member(findMember.get())
                    .postTitle(postCreateDto.getTitle())
                    .postContent(postCreateDto.getContent())
                    .build();

            post.createPost(findMember.get());
            postRepository.save(post);

            String imgPath = null;
            if(postCreateDto.getPostImgPath() != null && !postCreateDto.getPostImgPath().isEmpty()){
                for(MultipartFile file : postCreateDto.getPostImgPath()){
                    imgPath = s3UploadService.upload(file,"post_images");
                    ImageEntity newImage = ImageEntity.builder()
                            .post(post)
                            .imageUrl(imgPath)
                            .build();
                    imageRepository.save(newImage);
                }
            }
            CustomApiResponse<?> res = CustomApiResponse.createSuccess(HttpStatus.OK.value(),null,"게시물 작성 성공");
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        catch (DataAccessException dae) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CustomApiResponse.createFailWithout(HttpStatus.INTERNAL_SERVER_ERROR.value(), "데이터베이스 오류가 발생했습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CustomApiResponse.createFailWithout(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 오류가 발생했습니다."));
        }

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
        Member member = findMember.get();

/*        //응답할 때 제목, 내용, 이미지 , 작성자를 줘야함
        PostListDto.PostDto postResponse = new PostListDto.PostDto(
                post.getPostTitle(),
                post.getPostContent(),
                member.getMemberId(),
                post.getPostImgPath() != null && !post.getPostImgPath().isEmpty() ? post.getPostImgPath().get(0).getImageUrl() : null // 첫 번째 이미지 경로를 사용
        );*/
        CustomApiResponse<PostListDto.PostDto> res = CustomApiResponse.createSuccess(HttpStatus.OK.value(),null,"게시물 조회 성공");
        return ResponseEntity.ok(res);
    }
}
