package com.team.neorangloa.domain.comment.repository;

import com.team.neorangloa.domain.comment.entity.CommentRaid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CommentRaidRepository extends JpaRepository<CommentRaid, Long> {
    @Query("SELECT cr FROM CommentRaid cr WHERE cr.id = :commentRaidId AND cr.removed = false ")
    Optional<CommentRaid> findCommentRaidById(Long commentRaidId);

    @Query("SELECT cr FROM CommentRaid cr WHERE cr.postRaid.id = :postRaidId AND cr.removed = false ORDER BY cr.id DESC")
    List<CommentRaid> findCommentRaidByPostRaidId(Pageable pageable, Long postRaidId);

    @Transactional
    @Modifying
    @Query("UPDATE CommentRaid c SET c.recommendationCount = c.recommendationCount + 1 WHERE c.id = :commentRaidId")
    void increaseRecommendationCount(Long commentRaidId);

    @Transactional
    @Modifying
    @Query("UPDATE CommentRaid c SET c.recommendationCount = c.recommendationCount - 1 WHERE c.id = :commentRaidId")
    void decreaseRecommendationCount(Long commentRaidId);
}
