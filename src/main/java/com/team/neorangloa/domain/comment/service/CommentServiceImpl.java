package com.team.neorangloa.domain.comment.service;

import com.team.neorangloa.domain.comment.CommentMapper;
import com.team.neorangloa.domain.comment.dto.CommentRequest;
import com.team.neorangloa.domain.comment.entity.Comment;
import com.team.neorangloa.domain.comment.repository.CommentRepository;
import com.team.neorangloa.domain.post.entity.Post;
import com.team.neorangloa.domain.post.service.PostService;
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
}
