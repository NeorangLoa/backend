package com.team.neorangloa.domain.comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class CommentRequest { // 유저 정보는 나중에 추가
    @NotNull
    private final Long postId;
    @NotEmpty
    private final String content;
}
