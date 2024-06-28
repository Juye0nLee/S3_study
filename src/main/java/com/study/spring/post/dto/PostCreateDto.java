package com.study.spring.post.dto;

import com.study.spring.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class PostCreateDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req{
        @NotNull(message = "작성자 이름은 비어있을 수 없음")
        private String userName;

        @NotBlank
        private String title;

        @NotBlank
        private String content;

        @NotBlank
        private String pathImgPath;

        public Post toEntity(){
            return Post.builder()
                    .userName(userName)
                    .postTitle(title)
                    .postContent(content)
                    .postImgPath(pathImgPath)
                    .build();
        }
    }
}
