package com.team.neorangloa.domain.comment.entity;

import com.team.neorangloa.domain.post.entity.PostRaid;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "COMMENT_RAID")
@NoArgsConstructor
@Entity
public class CommentRaid extends BaseTimeEntity {
    @Id
    @Column(name = "COMMENT_RAID_ID")
    private Long id;

    @Column(nullable = false)
    @Lob
    private String content;

    @Column(name = "IS_REMOVED", nullable = false)
    private boolean removed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_RAID_ID", nullable = false)
    private PostRaid postRaid;
}
