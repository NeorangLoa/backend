package com.team.neorangloa.domain.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Builder
@RequiredArgsConstructor
public class PostResponse {

    private final Long postId;

    private final String nickname;
    private final String title;
    private final String content;
    private final List<String> imageUrls;
    private final int viewCounts;
    private final int recommendationCount;
    private final LocalDateTime createdAt;

}
