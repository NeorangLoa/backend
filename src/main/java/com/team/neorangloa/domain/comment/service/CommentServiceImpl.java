package com.team.neorangloa.domain.comment.service;

import com.team.neorangloa.domain.comment.CommentMapper;
import com.team.neorangloa.domain.comment.dto.CommentRequest;
import com.team.neorangloa.domain.comment.dto.CommentUpdateRequest;
import com.team.neorangloa.domain.comment.entity.Comment;
import com.team.neorangloa.domain.comment.repository.CommentRepository;
import com.team.neorangloa.domain.post.entity.Post;
import com.team.neorangloa.domain.post.service.PostService;
import com.team.neorangloa.global.error.ErrorCode;
import com.team.neorangloa.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    private final PostService postService;

    @Override
    @Transactional
    public void createComment(CommentRequest commentRequest) {
        Post post = postService.findPostById(commentRequest.getPostId());
        Comment comment = commentMapper.toEntity(commentRequest, post);
        commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Long commentId) {
        return commentRepository.findCommentById(commentId).orElseThrow(
                () -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND_ERROR));
    }

    @Transactional
    @Override
    public void updateComment(Comment comment, CommentUpdateRequest request) {
        comment.updateComment(request);
    }

    @Transactional
    @Override
    public void deleteComment(Comment comment) {
        comment.setRemoved(true);
        commentRepository.save(comment);
    }
}
