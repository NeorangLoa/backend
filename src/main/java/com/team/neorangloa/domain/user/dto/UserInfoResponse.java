package com.team.neorangloa.domain.user.dto;

import com.team.neorangloa.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class UserInfoResponse {
    private final String email;
    private final String nickname;

    public static UserInfoResponse of(User user) {
        return UserInfoResponse.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }
}
