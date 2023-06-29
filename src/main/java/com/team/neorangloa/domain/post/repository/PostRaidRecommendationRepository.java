package com.team.neorangloa.domain.post.repository;

import com.team.neorangloa.domain.post.entity.PostRaid;
import com.team.neorangloa.domain.post.entity.PostRaidRecommendation;
import com.team.neorangloa.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRaidRecommendationRepository extends JpaRepository<PostRaidRecommendation, Long> {
    @Query("SELECT pr FROM PostRaidRecommendation pr WHERE pr.postRaid = :postRaid AND pr.user = :user")
    Optional<PostRaidRecommendation> findByUserAndPostRaid(@Param("user") User user, @Param("postRaid") PostRaid postRaid);

    void deleteByUserAndPostRaid(User user, PostRaid postRaid);
}
