package com.team.neorangloa.domain.user.dto;

import com.team.neorangloa.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class ProfileResponse {
    private final String email;
    private final String nickname;

    public static ProfileResponse of(User user) {
        return ProfileResponse.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }
}
