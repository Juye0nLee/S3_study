package com.study.spring.post.dto;

import lombok.*;

import java.util.List;

public class PostListDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostDto{
        private String title;
        private String content;
        private String userName;
        private String imageUrl;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    public static class SearchPostRes{
        private List<PostDto> posts;

        public SearchPostRes(List<PostDto> posts) {
            this.posts = posts;
        }
    }
}
