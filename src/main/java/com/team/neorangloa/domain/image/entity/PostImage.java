package com.team.neorangloa.domain.image.entity;

import com.team.neorangloa.domain.post.entity.Post;
import com.team.neorangloa.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_IMAGE_ID")
    private Long id;

    @Column(name = "POST_IMAGE_NAME")
    private String name;

    @Column(name = "POST_IMAGE_URL")
    private String url;

    @Column(name = "IS_REMOVED")
    private boolean removed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @Builder
    public PostImage(String name, String url, Post post) {
        this.name = name;
        this.url = url;
        this.post = post;
    }
}
