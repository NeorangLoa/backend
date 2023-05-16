package com.team.neorangloa.domain.raid.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "RAID")
@NoArgsConstructor
@Entity
public class Raid {
    @Id
    @Column(name = "RAID_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String level;

    @Column(name = "ITEM_LEVEL")
    private String itemLevel;
}
