package com.study.spring.member.dto;

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
        @NotEmpty(message = "아이디를 입력해주세요")
        private String memberId;

        @NotEmpty(message = "비밀번호를  입력해주세요")
        private String password;
    }
}
