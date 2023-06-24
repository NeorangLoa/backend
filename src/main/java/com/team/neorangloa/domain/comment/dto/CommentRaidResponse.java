package com.team.neorangloa.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CommentRaidResponse {
    private final Long commentRaidId;
    private final String nickname;
    private final String content;
}
