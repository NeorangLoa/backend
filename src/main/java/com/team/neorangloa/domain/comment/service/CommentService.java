package com.team.neorangloa.domain.comment.service;

import com.team.neorangloa.domain.comment.dto.CommentRequest;

public interface CommentService {
    public void createComment(CommentRequest commentRequest);
}
