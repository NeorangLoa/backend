package com.team.neorangloa.domain.comment.entity;

import com.team.neorangloa.domain.comment.dto.CommentUpdateRequest;
import com.team.neorangloa.domain.post.entity.Post;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "COMMENT")
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Lob
    private String content;

    @Column(name = "IS_REMOVED", nullable = false)
    private boolean removed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User author;

    @Column(name = "RECOMMENDATION_COUNT", nullable = false)
    private int recommendationCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    @Builder
    public Comment(String content, boolean removed, User author, Post post) {
        this.content = content;
        this.removed = removed;
        this.author = author;
        this.post = post;
    }

    public void updateComment(CommentUpdateRequest request) {
        this.content = request.getContent();
    }

    public void setRemoved(boolean removed) { this.removed = removed; }
}
