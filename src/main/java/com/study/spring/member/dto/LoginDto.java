package com.study.spring.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class LoginDto {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Req {
        @NotNull(message = "아이디를 입력해주세요")
        private String memberId;

        @NotNull(message = "비밀번호를  입력해주세요")
        private String password;
    }
}
