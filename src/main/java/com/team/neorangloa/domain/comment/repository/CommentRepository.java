package com.team.neorangloa.domain.comment.repository;

import com.team.neorangloa.domain.comment.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.id = :commentId AND c.removed = false ")
    Optional<Comment> findCommentById(Long commentId);

    @Query("SELECT c FROM Comment c WHERE c.post.id = :postId AND c.removed = false ORDER BY c.id DESC")
    List<Comment> findCommentByPostId(Pageable pageable, Long postId);

    @Transactional
    @Modifying
    @Query("UPDATE Comment c SET c.recommendationCount = c.recommendationCount + 1 WHERE c.id = :commentId")
    void increaseRecommendationCount(Long commentId);

    @Transactional
    @Modifying
    @Query("UPDATE Comment c SET c.recommendationCount = c.recommendationCount - 1 WHERE c.id = :commentId")
    void decreaseRecommendationCount(Long commentId);
}
