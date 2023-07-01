package com.team.neorangloa.domain.post.repository;

import com.team.neorangloa.domain.post.entity.Post;
import com.team.neorangloa.domain.post.entity.PostRecommendation;
import com.team.neorangloa.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRecommendationRepository extends JpaRepository<PostRecommendation, Long> {
    @Query("SELECT pr FROM PostRecommendation pr WHERE pr.post = :post AND pr.user = :user")
    Optional<PostRecommendation> findByClientAndPost(@Param("user") User user, @Param("post") Post post);

    void deleteByUserAndPost(User user, Post post);
}
