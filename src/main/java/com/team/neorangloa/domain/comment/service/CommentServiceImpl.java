package com.team.neorangloa.domain.comment.service;

import com.team.neorangloa.domain.comment.CommentMapper;
import com.team.neorangloa.domain.comment.dto.CommentRequest;
import com.team.neorangloa.domain.comment.dto.CommentResponse;
import com.team.neorangloa.domain.comment.dto.CommentUpdateRequest;
import com.team.neorangloa.domain.comment.entity.Comment;
import com.team.neorangloa.domain.comment.exception.CommentAuthorMismatchException;
import com.team.neorangloa.domain.comment.repository.CommentRepository;
import com.team.neorangloa.domain.post.entity.Post;
import com.team.neorangloa.domain.post.service.PostService;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.error.ErrorCode;
import com.team.neorangloa.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    private final PostService postService;

    @Override
    @Transactional
    public void createComment(CommentRequest commentRequest, User loginUser) {
        Post post = postService.findPostById(commentRequest.getPostId());
        Comment comment = commentMapper.toEntity(commentRequest, post, loginUser);
        commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Long commentId) {
        return commentRepository.findCommentById(commentId).orElseThrow(
                () -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND_ERROR));
    }

    @Transactional
    @Override
    public void updateComment(User loginUser, Comment comment, CommentUpdateRequest request) {
        checkIsAuthor(loginUser, comment);
        comment.updateComment(request);
    }

    @Transactional
    @Override
    public void deleteComment(User loginUser, Comment comment) {
        checkIsAuthor(loginUser, comment);
        comment.setRemoved(true);
        commentRepository.save(comment);
    }

    @Transactional
    @Override
    public List<CommentResponse> findCommentByPostId(Integer page, Integer size, Long postId) {
        PageRequest pageRequest = PageRequest.of(page,size);
        List<Comment> comments = commentRepository.findCommentByPostId(pageRequest, postId);
        return commentMapper.toDtoList(comments);
    }

    @Transactional
    @Override
    public void checkIsAuthor(User loginUser, Comment comment){
        if (!loginUser.getId().equals(comment.getAuthor().getId())) {
            throw new CommentAuthorMismatchException();
        }
    }
}
