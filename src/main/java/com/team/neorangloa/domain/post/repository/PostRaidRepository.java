package com.team.neorangloa.domain.post.repository;

import com.team.neorangloa.domain.post.entity.PostRaid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PostRaidRepository extends JpaRepository<PostRaid, Long> {
    @Query("SELECT pr FROM PostRaid pr WHERE pr.id = :postRaidId AND pr.removed = false ")
    Optional<PostRaid> findPostRaidById(Long postRaidId);

    @Query("SELECT pr FROM PostRaid pr WHERE pr.removed = false ORDER BY pr.id DESC")
    Page<PostRaid> findAllPostRaidByCreatedAtDesc(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE PostRaid pr SET pr.viewCounts = pr.viewCounts + 1 WHERE pr.id = :postRaidId")
    void updateViewCounts(Long postRaidId);
    @Transactional
    @Modifying
    @Query("UPDATE PostRaid pr SET pr.recommendationCount = pr.recommendationCount + 1 WHERE pr.id = :postRaidId")
    void increaseRecommendationCount(Long postRaidId);
    @Transactional
    @Modifying
    @Query("UPDATE PostRaid pr SET pr.recommendationCount = pr.recommendationCount - 1 WHERE pr.id = :postRaidId")
    void decreaseRecommendationCount(Long postRaidId);
}
