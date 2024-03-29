package com.team.neorangloa.domain.comment;

import com.team.neorangloa.domain.comment.dto.CommentRaidRequest;
import com.team.neorangloa.domain.comment.dto.CommentRaidResponse;
import com.team.neorangloa.domain.comment.entity.CommentRaid;
import com.team.neorangloa.domain.post.entity.PostRaid;
import com.team.neorangloa.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentRaidMapper {
    public CommentRaid toEntity(CommentRaidRequest commentRaidRequest, PostRaid postRaid, User loginUser) {
        return CommentRaid.builder()
                .content(commentRaidRequest.getContent())
                .removed(false)
                .postRaid(postRaid)
                .author(loginUser)
                .build();
    }

    public CommentRaidResponse toDto(CommentRaid commentRaid) {
        return CommentRaidResponse.builder()
                .commentRaidId(commentRaid.getId())
                .nickname(commentRaid.getAuthor().getNickname())
                .content(commentRaid.getContent())
                .recommendationCount(commentRaid.getRecommendationCount())
                .createdAt(commentRaid.getCreatedTime())
                .build();
    }

    public List<CommentRaidResponse> toDtoList(List<CommentRaid> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

}
