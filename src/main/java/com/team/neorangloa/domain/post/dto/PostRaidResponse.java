package com.team.neorangloa.domain.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class PostRaidResponse {
    private final Long postRaidId;
    private final String nickname;
    private final String title;
    private final String content;
    private final int viewCounts;
    private final int maxAttacker;
    private final int maxSupporter;
    private final LocalDateTime finishedAt;
    private final String raidName;
    private final String raidItemLevel;
    private final LocalDateTime createdAt;
}
