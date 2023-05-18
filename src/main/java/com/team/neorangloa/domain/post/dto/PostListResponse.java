package com.team.neorangloa.domain.post.dto;

import com.team.neorangloa.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@RequiredArgsConstructor
public class PostListResponse {
    private final Long postId;

    private final String nickname;
    private final String title;

}
