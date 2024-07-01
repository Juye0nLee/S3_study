package com.study.spring.post.dto;

import com.study.spring.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class PostCreateDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req{

        @NotEmpty(message = "작성자의 기본키는 필수입니다.")
        private Long id;

        @NotEmpty(message = "제목을 입력해주세요")
        private String title;

        @NotEmpty(message = "내용을 입력해주세요")
        private String content;

        @NotEmpty(message = "이미지를 넣어주세요")
        private String pathImgPath;

        public Post toEntity(){
            return Post.builder()
                    .postTitle(title)
                    .postContent(content)
                    .postImgPath(pathImgPath)
                    .build();
        }
    }
}
