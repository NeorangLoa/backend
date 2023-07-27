package com.team.neorangloa.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostRaidRequest {
    @NotEmpty
    @Length(max = 100, message = "제목은 최대 100글자를 넘을 수 없습니다.")
    private String title;

    @NotEmpty
    private String content;

    @NotNull
    @Max(8)
    private int maxAttacker;

    @NotNull
    @Max(8)
    private int maxSupporter;

    @NotNull
    private LocalDateTime finishedAt;

    // 레이드 정보
    @NotEmpty
    private String raidName;
    @NotEmpty
    private String raidItemLevel;

}
