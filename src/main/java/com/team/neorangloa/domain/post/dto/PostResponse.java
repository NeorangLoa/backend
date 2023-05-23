package com.team.neorangloa.domain.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@Builder
@RequiredArgsConstructor
public class PostResponse {

    private final Long postId;

    private final String nickname;
    private final String title;

    private final String content;
    private final String image;

}
