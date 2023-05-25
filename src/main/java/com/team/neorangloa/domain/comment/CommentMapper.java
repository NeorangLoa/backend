package com.team.neorangloa.domain.comment;

import com.team.neorangloa.domain.comment.dto.CommentRequest;
import com.team.neorangloa.domain.comment.entity.Comment;
import com.team.neorangloa.domain.post.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment toEntity(CommentRequest commentRequest, Post post) {
        return Comment.builder()
                .content(commentRequest.getContent())
                .removed(false)
                .post(post)
                .build();

    }

}
