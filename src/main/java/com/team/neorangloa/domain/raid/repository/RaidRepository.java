package com.team.neorangloa.domain.raid.repository;

import com.team.neorangloa.domain.raid.entity.Raid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RaidRepository extends JpaRepository<Raid, Long> {
    Optional<Raid> findRaidByName(String raidName);
}
