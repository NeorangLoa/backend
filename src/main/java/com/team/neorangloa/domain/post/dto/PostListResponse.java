package com.team.neorangloa.domain.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Getter
@Builder
@RequiredArgsConstructor
public class PostListResponse {
    private final Long postId;
    private final String nickname;
    private final String title;
    private final int viewCounts;
    private final int recommendationCount;
    private final LocalDateTime createdAt;
}
