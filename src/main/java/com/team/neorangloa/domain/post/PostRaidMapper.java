package com.team.neorangloa.domain.post;

import com.team.neorangloa.domain.post.dto.PostRaidListResponse;
import com.team.neorangloa.domain.post.dto.PostRaidRequest;
import com.team.neorangloa.domain.post.dto.PostRaidResponse;
import com.team.neorangloa.domain.post.entity.PostRaid;
import com.team.neorangloa.domain.raid.entity.Raid;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostRaidMapper {

    public PostRaid toEntity(PostRaidRequest postRaidRequest) {
        return PostRaid.builder()
                .title(postRaidRequest.getTitle())
                .content(postRaidRequest.getContent())
                .maxAttacker(postRaidRequest.getMaxAttacker())
                .maxSupporter(postRaidRequest.getMaxSupporter())
                .finishedAt(postRaidRequest.getFinishedAt())
                .raid(Raid.builder()
                        .name(postRaidRequest.getRaidName())
                        .level(postRaidRequest.getRaidLevel())
                        .itemLevel(postRaidRequest.getRaidItemLevel())
                        .build())
                .removed(false)
                .build();
    }

    @Builder
    public static PostRaidResponse of(PostRaid postRaid) {
        return PostRaidResponse.builder()
                .postRaidId(postRaid.getId())
                .title(postRaid.getTitle())
                .content(postRaid.getContent())
                .maxAttacker(postRaid.getMaxAttacker())
                .maxSupporter(postRaid.getMaxSupporter())
                .finishedAt(postRaid.getFinishedAt())
                .raidName(postRaid.getRaid().getName())
                .raidLevel(postRaid.getRaid().getLevel())
                .raidItemLevel(postRaid.getRaid().getItemLevel())
                .build();
    }

    public PostRaidListResponse toDto(PostRaid postRaid) {
        return PostRaidListResponse.builder()
                .postRaidId(postRaid.getId())
                .title(postRaid.getTitle())
                .maxAttacker(postRaid.getMaxAttacker())
                .maxSupporter(postRaid.getMaxSupporter())
                .raidName(postRaid.getRaid().getName())
                .raidLevel(postRaid.getRaid().getLevel())
                .raidItemLevel(postRaid.getRaid().getItemLevel())
                .build();
    }

    public List<PostRaidListResponse> toDtoList(List<PostRaid> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
