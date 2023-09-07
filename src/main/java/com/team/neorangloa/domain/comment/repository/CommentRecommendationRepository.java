package com.team.neorangloa.domain.comment.repository;

import com.team.neorangloa.domain.comment.entity.Comment;
import com.team.neorangloa.domain.comment.entity.CommentRecommendation;
import com.team.neorangloa.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentRecommendationRepository extends JpaRepository<CommentRecommendation, Long>{
    @Query("SELECT pr FROM CommentRecommendation  pr WHERE pr.comment = :comment AND pr.user = :user")
    Optional<CommentRecommendation> findByClientAndComment(@Param("user") User user, @Param("comment")Comment comment);

    void deleteByUserAndComment(User user, Comment comment);
}
