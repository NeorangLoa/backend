package com.team.neorangloa.domain.post.entity;

import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "POST_RAID_RECOMMENDATION")
@NoArgsConstructor
public class PostRaidRecommendation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECOMMENDATION_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_RAID_ID", nullable = false)
    private PostRaid postRaid;

    private boolean isRecommended;

    public void setRecommended(boolean recommended) {
        this.isRecommended = recommended;
    }

    @Builder
    public PostRaidRecommendation(User user, PostRaid postRaid, boolean isRecommended){
        this.user = user;
        this.postRaid = postRaid;
        this.isRecommended = isRecommended;
    }
}