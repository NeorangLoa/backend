package com.team.neorangloa.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class CommentRaidResponse {
    private final Long commentRaidId;
    private final String nickname;
    private final String content;
    private final int recommendationCount;
    private final LocalDateTime createdAt;
}
