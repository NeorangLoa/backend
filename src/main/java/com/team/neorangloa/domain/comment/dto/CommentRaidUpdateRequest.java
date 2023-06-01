package com.team.neorangloa.domain.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class CommentRaidUpdateRequest {
    @NotEmpty
    private String content;
}
