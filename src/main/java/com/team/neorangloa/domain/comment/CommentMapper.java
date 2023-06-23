package com.team.neorangloa.domain.comment;

import com.team.neorangloa.domain.comment.dto.CommentRequest;
import com.team.neorangloa.domain.comment.dto.CommentResponse;
import com.team.neorangloa.domain.comment.entity.Comment;
import com.team.neorangloa.domain.post.entity.Post;
import com.team.neorangloa.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper {
    public Comment toEntity(CommentRequest commentRequest, Post post, User loginUser) {
        return Comment.builder()
                .content(commentRequest.getContent())
                .removed(false)
                .post(post)
                .author(loginUser)
                .build();

    }

    public CommentResponse toDto(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .build();
    }

    public List<CommentResponse> toDtoList(List<Comment> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
