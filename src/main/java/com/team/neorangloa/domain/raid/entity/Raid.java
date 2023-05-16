package com.team.neorangloa.domain.raid.entity;

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
}
