package com.team.neorangloa.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class LoginRequest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    @Builder
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
