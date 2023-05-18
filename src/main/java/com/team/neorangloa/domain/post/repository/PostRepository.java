package com.team.neorangloa.domain.post.repository;

import com.team.neorangloa.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
