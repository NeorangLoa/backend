package com.team.neorangloa.domain.comment.service;

import com.team.neorangloa.domain.comment.dto.CommentRaidRequest;
import com.team.neorangloa.domain.comment.dto.CommentRaidResponse;
import com.team.neorangloa.domain.comment.dto.CommentRaidUpdateRequest;
import com.team.neorangloa.domain.comment.entity.CommentRaid;
import com.team.neorangloa.domain.post.entity.PostRaid;
import com.team.neorangloa.domain.user.entity.User;

import java.util.List;

public interface CommentRaidService {
    public void createCommentRaid(CommentRaidRequest commentRaidRequest, User loginUser);

    public CommentRaid findCommentRaidById(Long commentRaidId);

    public void updateCommentRaid(User user, CommentRaid commentRaid, CommentRaidUpdateRequest request);
    public void deleteCommentRaid(User user, CommentRaid commentRaid);
    public List<CommentRaidResponse> findCommentRaidByPostRaidId(Integer page, Integer size, Long postRaidId);
    public void checkIsAuthor(User user, CommentRaid commentRaid);
    public int updateCommentRaidRecommendation(User loginUser, CommentRaid commentRaid);
}
