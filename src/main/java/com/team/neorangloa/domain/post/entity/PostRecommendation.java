package com.team.neorangloa.domain.post.entity;

import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "POST_RECOMMENDATION")
@NoArgsConstructor
public class PostRecommendation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RAID_RECOMMENDATION_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    private boolean isRecommended;

    public void setRecommended(boolean recommended) {
        this.isRecommended = recommended;
    }

    @Builder
    public PostRecommendation(User user, Post post, boolean isRecommended){
        this.user = user;
        this.post = post;
        this.isRecommended = isRecommended;
    }
}
