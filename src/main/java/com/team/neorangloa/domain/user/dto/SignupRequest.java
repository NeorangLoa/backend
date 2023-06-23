package com.team.neorangloa.domain.user.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class SignupRequest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String nickname;
}
