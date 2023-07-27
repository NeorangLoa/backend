package com.team.neorangloa.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRaidRequest { // 유저 정보는 나중에 추가
    @NotNull
    private Long postRaidId;
    @NotEmpty
    private String content;
}
