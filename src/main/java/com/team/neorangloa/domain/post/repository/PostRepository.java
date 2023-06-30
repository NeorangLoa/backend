package com.team.neorangloa.domain.post.repository;

import com.team.neorangloa.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.id = :postId AND p.removed = false ")
    Optional<Post> findPostById(Long postId);

    // 검색 조건 없는 모든 게시글 조회
    @Query("SELECT p FROM Post p WHERE p.removed = false ORDER BY p.id DESC")
    Page<Post> findAllPostByCreatedAtDesc(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Post p SET p.viewCounts = p.viewCounts + 1 WHERE p.id = :postId")
    void updateViewCounts(Long postId);

    @Transactional
    @Modifying
    @Query("UPDATE Post p SET p.recommendationCount = p.recommendationCount + 1 WHERE p.id = :postId")
    void increaseRecommendationCount(Long postId);

    @Transactional
    @Modifying
    @Query("UPDATE Post p SET p.recommendationCount = p.recommendationCount - 1 WHERE p.id = :postId")
    void decreaseRecommendationCount(Long postId);
}
