package com.team.neorangloa.domain.comment.entity;

import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.entity.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @Column(name = "COMMENT_ID")
    private Long id;

    private String content;

    @Column(name = "IS_REMOVED")
    private boolean removed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User author;


}
