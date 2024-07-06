package com.study.spring.post.dto;

import com.study.spring.domain.Post;
import com.study.spring.util.entity.ImageEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

public class PostCreateDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req{

        @NotNull(message = "작성자의 기본키는 필수입니다.")
        private Long id;

        @NotNull(message = "제목을 입력해주세요")
        private String title;

        @NotNull(message = "내용을 입력해주세요")
        private String content;

        private List<MultipartFile> postImgPath;

    }
}
