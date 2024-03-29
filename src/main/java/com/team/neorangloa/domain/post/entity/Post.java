package com.team.neorangloa.domain.post.entity;

import com.team.neorangloa.domain.post.dto.PostRequest;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Table(name = "POST")
@DynamicInsert
@DynamicUpdate
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

    @ColumnDefault("0")
    @Column(name = "view_counts",nullable = false)
    private int viewCounts;

    @Column(name="IS_REMOVED", nullable = false)
    private boolean removed;

    @Column(name = "RECOMMENDATION_COUNT", nullable = false)
    private int recommendationCount;

    @Builder
    public Post(String title, String content, User author, int viewCounts, boolean removed){
        this.title = title;
        this.content= content;
        this.author = author;
        this.viewCounts = viewCounts;
        this.removed = removed;
    }

    public void updatePost(PostRequest postRequest) {
        this.title = postRequest.getTitle();
        this.content= postRequest.getContent();
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

}
