package com.team.neorangloa.domain.comment.service;

import com.team.neorangloa.domain.comment.dto.CommentRequest;
import com.team.neorangloa.domain.comment.dto.CommentUpdateRequest;
import com.team.neorangloa.domain.comment.entity.Comment;

public interface CommentService {
    public void createComment(CommentRequest commentRequest);

    public Comment findCommentById(Long commentId);

    public void updateComment(Comment comment, CommentUpdateRequest request);
}
