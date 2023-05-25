package com.team.neorangloa.domain.user.dto;

import lombok.Getter;

@Getter
public class SignupRequest {
    private String email;

    private String password;

    private String nickname;
}
