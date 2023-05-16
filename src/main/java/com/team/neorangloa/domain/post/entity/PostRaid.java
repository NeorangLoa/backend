package com.team.neorangloa.domain.post.entity;

import com.team.neorangloa.domain.raid.entity.Raid;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "POST_RAID")
@NoArgsConstructor
@Entity
public class PostRaid extends BaseTimeEntity {
    @Id
    @Column(name = "POST_RAID_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(name = "IS_REMOVED")
    private boolean removed;

    @Column(name = "MAX_ATTACKER")
    private int maxAttacker;

    @Column(name = "MAX_SUPPORTER")
    private int maxSupporter;

    @Column(name = "FINISHED_AT")
    private LocalDateTime finishedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RAID_ID")
    private Raid raid;
}
