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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User author;

    private String post_image;

    @Column(name="IS_REMOVED", nullable = false)
    private boolean removed;

    @Builder
    public Post(String title, String content, User author, String post_image, boolean removed){
        this.title = title;
        this.content= content;
        this.author = author;
        this.post_image = post_image;
        this.removed = removed;
    }

}
