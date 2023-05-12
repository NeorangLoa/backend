package com.team.neorangloa.domain.post.entity;

import com.team.neorangloa.global.entity.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Getter
@Entity
public class PostRaid extends BaseTimeEntity {
    @Id
    @Column(name = "POST_RAID_ID")
    private Long id;

    private String title;

    @Lob
    private String content;

    @Column(name = "IS_REMOVED")
    private boolean removed;

    @
}
