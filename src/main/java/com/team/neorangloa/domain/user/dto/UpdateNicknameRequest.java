package com.team.neorangloa.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

//@Builder
@Getter
@NoArgsConstructor
public class UpdateNicknameRequest {
    @NotEmpty
    private String nickname;
}
