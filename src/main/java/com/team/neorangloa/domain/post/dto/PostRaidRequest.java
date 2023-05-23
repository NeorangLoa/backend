package com.team.neorangloa.domain.post.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class PostRaidRequest {
    @NotEmpty
    @Length(max = 100, message = "제목은 최대 100글자를 넘을 수 없습니다.")
    private final String title;

    @NotEmpty
    private final String content;

    @NotNull
    @Max(8)
    private final int maxAttacker;

    @NotNull
    @Max(8)
    private final int maxSupporter;

    @NotNull
    private final LocalDateTime finishedAt;

    // 레이드 정보
    @NotEmpty
    private final String raidName;
    @NotEmpty
    private final String raidItemLevel;

}
