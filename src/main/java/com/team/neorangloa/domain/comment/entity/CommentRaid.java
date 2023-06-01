package com.team.neorangloa.domain.comment.entity;

import com.team.neorangloa.domain.comment.dto.CommentRaidUpdateRequest;
import com.team.neorangloa.domain.comment.dto.CommentUpdateRequest;
import com.team.neorangloa.domain.post.entity.PostRaid;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.entity.BaseTimeEntity;
import lombok.Builder;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_RAID_ID", nullable = false)
    private PostRaid postRaid;

    @Builder
    public CommentRaid(String content, boolean removed, User author, PostRaid postRaid) {
        this.content = content;
        this.removed = removed;
        this.author = author;
        this.postRaid = postRaid;
    }

    public void updateCommentRaid(CommentRaidUpdateRequest request) {
        this.content = request.getContent();
    }

    public void setRemoved(boolean removed) { this.removed = removed; }
}
