package com.team.neorangloa.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

//@Builder
@Getter
@NoArgsConstructor
public class UpdateNicknameRequest {

    private String nickname;
}
