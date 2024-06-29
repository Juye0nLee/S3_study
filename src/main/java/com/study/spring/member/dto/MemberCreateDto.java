package com.study.spring.member.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;

public class MemberCreateDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Req {
        @NotNull(message = "이름을 입력해주세요")
        private String name;

        @NotNull(message = "비밀번호를 입력해주세요")
        private String password;

        @NotNull(message = "핸드폰 번호를 입력해주세요")
        private String phone;

        @NotNull(message = "이메일을 입력해주세요")
        private String email;

        @NotNull(message = "프로필 이미지를 설정해주세요")
        private String profileImg;
    }
}
