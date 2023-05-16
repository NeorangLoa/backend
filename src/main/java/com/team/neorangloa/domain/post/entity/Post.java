package com.team.neorangloa.domain.post.entity;

import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "POST")
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @Column(name = "POST_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID", nullable = false)
    private User author;

    private String post_image;

    @Column(name="IS_REMOVED", nullable = false)
    private boolean removed;

}
