package com.team.neorangloa.domain.comment.entity;

import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name= "COMMENT_RECOMMENDATION")
@NoArgsConstructor
public class CommentRecommendation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMENT_ID", nullable = false)
    private Comment comment;

    private boolean isRecommended;

    public void setRecommended(boolean recommended){
        this.isRecommended = recommended;
    }
    @Builder
    public CommentRecommendation(User user, Comment comment, boolean isRecommended) {
        this.user = user;
        this.comment = comment;
        this.isRecommended = isRecommended;
    }
}
