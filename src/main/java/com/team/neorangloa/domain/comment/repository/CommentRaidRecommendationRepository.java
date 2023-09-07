package com.team.neorangloa.domain.comment.repository;

import com.team.neorangloa.domain.comment.entity.Comment;
import com.team.neorangloa.domain.comment.entity.CommentRaid;
import com.team.neorangloa.domain.comment.entity.CommentRaidRecommendation;
import com.team.neorangloa.domain.comment.entity.CommentRecommendation;
import com.team.neorangloa.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentRaidRecommendationRepository extends JpaRepository<CommentRaidRecommendation, Long> {
    @Query("SELECT pr FROM CommentRaidRecommendation  pr WHERE pr.commentRaid = :commentRaid AND pr.user = :user")
    Optional<CommentRaidRecommendation> findByClientAndComment(@Param("user") User user, @Param("commentRaid") CommentRaid commentRaid);

    void deleteByUserAndCommentRaid(User user, CommentRaid commentRaid);
}
