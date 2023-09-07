package com.team.neorangloa.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class CommentResponse { // 유저 정보는 나중에 추가
    private final Long commentId;
    private final String nickname;
    private final String content;
    private final int recommendationCount;
    private final LocalDateTime createdAt;

}
