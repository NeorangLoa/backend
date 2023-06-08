package com.team.neorangloa.domain.comment.service;

import com.team.neorangloa.domain.comment.dto.CommentRaidRequest;
import com.team.neorangloa.domain.comment.dto.CommentRaidResponse;
import com.team.neorangloa.domain.comment.dto.CommentRaidUpdateRequest;
import com.team.neorangloa.domain.comment.entity.CommentRaid;

import java.util.List;

public interface CommentRaidService {
    public void createCommentRaid(CommentRaidRequest commentRaidRequest);

    public CommentRaid findCommentRaidById(Long commentRaidId);

    public void updateCommentRaid(CommentRaid commentRaid, CommentRaidUpdateRequest request);
    public void deleteCommentRaid(CommentRaid commentRaid);
    public List<CommentRaidResponse> findCommentRaidByPostRaidId(Integer page, Integer size, Long postRaidId);
}
