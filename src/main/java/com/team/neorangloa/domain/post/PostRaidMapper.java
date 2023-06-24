package com.team.neorangloa.domain.post;

import com.team.neorangloa.domain.post.dto.PostRaidListResponse;
import com.team.neorangloa.domain.post.dto.PostRaidRequest;
import com.team.neorangloa.domain.post.dto.PostRaidResponse;
import com.team.neorangloa.domain.post.entity.PostRaid;
import com.team.neorangloa.domain.raid.entity.Raid;
import com.team.neorangloa.domain.user.entity.User;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostRaidMapper {

    public PostRaid toEntity(PostRaidRequest postRaidRequest, User loginUser) {
        return PostRaid.builder()
                .title(postRaidRequest.getTitle())
                .content(postRaidRequest.getContent())
                .maxAttacker(postRaidRequest.getMaxAttacker())
                .maxSupporter(postRaidRequest.getMaxSupporter())
                .finishedAt(postRaidRequest.getFinishedAt())
                .raid(Raid.builder()
                        .name(postRaidRequest.getRaidName())
                        .itemLevel(postRaidRequest.getRaidItemLevel())
                        .build())
                .removed(false)
                .author(loginUser)
                .build();
    }

    @Builder
    public static PostRaidResponse of(PostRaid postRaid) {
        return PostRaidResponse.builder()
                .postRaidId(postRaid.getId())
                .title(postRaid.getTitle())
                .nickname(postRaid.getAuthor().getNickname())
                .content(postRaid.getContent())
                .viewCounts(postRaid.getViewCounts())
                .maxAttacker(postRaid.getMaxAttacker())
                .maxSupporter(postRaid.getMaxSupporter())
                .finishedAt(postRaid.getFinishedAt())
                .raidName(postRaid.getRaid().getName())
                .raidItemLevel(postRaid.getRaid().getItemLevel())
                .build();
    }

    public PostRaidListResponse toDto(PostRaid postRaid) {
        return PostRaidListResponse.builder()
                .postRaidId(postRaid.getId())
                .title(postRaid.getTitle())
                .nickname(postRaid.getAuthor().getNickname())
                .viewCounts(postRaid.getViewCounts())
                .maxAttacker(postRaid.getMaxAttacker())
                .maxSupporter(postRaid.getMaxSupporter())
                .raidName(postRaid.getRaid().getName())
                .raidItemLevel(postRaid.getRaid().getItemLevel())
                .build();
    }

    public List<PostRaidListResponse> toDtoList(List<PostRaid> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
