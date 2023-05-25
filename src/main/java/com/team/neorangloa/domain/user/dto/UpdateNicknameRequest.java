package com.team.neorangloa.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//@Builder
@Getter
@RequiredArgsConstructor
public class UpdateNicknameRequest {
    private final String email;
    private final String nickname;
}
