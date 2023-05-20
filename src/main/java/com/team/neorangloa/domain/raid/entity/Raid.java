package com.team.neorangloa.domain.raid.entity;

import com.team.neorangloa.domain.post.dto.PostRaidRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "RAID")
@NoArgsConstructor
@Entity
public class Raid {
    @Id
    @Column(name = "RAID_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String level;

    @Column(name = "ITEM_LEVEL")
    private String itemLevel;

    @Builder
    public Raid(String name, String level, String itemLevel) {
        this.name = name;
        this.level = level;
        this.itemLevel = itemLevel;
    }

    public void updateRaid(PostRaidRequest postRaidRequest) {
        this.name = postRaidRequest.getRaidName();
        this.level = postRaidRequest.getRaidLevel();
        this.itemLevel = postRaidRequest.getRaidItemLevel();
    }
}
