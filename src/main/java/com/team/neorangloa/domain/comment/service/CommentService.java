package com.team.neorangloa.domain.comment.service;

import com.team.neorangloa.domain.comment.dto.CommentRequest;
import com.team.neorangloa.domain.comment.dto.CommentResponse;
import com.team.neorangloa.domain.comment.dto.CommentUpdateRequest;
import com.team.neorangloa.domain.comment.entity.Comment;
import com.team.neorangloa.domain.user.entity.User;

import java.util.List;

public interface CommentService {
    public void createComment(CommentRequest commentRequest, User loginUser);

    public Comment findCommentById(Long commentId);

    public void updateComment(User user, Comment comment, CommentUpdateRequest request);
    public void deleteComment(User user, Comment comment);
    public List<CommentResponse> findCommentByPostId(Integer page, Integer size, Long postId);
    public void checkIsAuthor(User user, Comment comment);
}
