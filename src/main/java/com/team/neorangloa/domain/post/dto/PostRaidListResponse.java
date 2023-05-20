package com.team.neorangloa.domain.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@Builder
@RequiredArgsConstructor
public class PostRaidListResponse {
    private final Long postRaidId;
    private final String nickname;
    private final String title;
    private final int maxAttacker;
    private final int maxSupporter;
    private final String raidName;
    private final String raidLevel;
    private final String raidItemLevel;
}
