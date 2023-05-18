package com.team.neorangloa.domain.post.repository;

import com.team.neorangloa.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.id = :postId AND p.removed = false ")
    Optional<Post> findPostById(Long postId);
}
