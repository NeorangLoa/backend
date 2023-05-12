package com.team.neorangloa.domain.raid.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
public class Raid {
    @Id
    @Column(name = "RAID_ID")
    private Long id;

    private String name;

    private String level;

    @Column(name = "ITEM_LEVEL")
    private String itemLevel;
}
