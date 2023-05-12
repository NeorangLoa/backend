package com.team.neorangloa.domain.post.entity;

import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @Column(name = "POST_ID")
    private Long id;

    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User author;

    private String post_image;

    @Column(name="IS_REMOVED")
    private boolean removed = false;

}
