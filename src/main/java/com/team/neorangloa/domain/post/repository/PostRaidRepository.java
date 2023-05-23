package com.team.neorangloa.domain.post.repository;

import com.team.neorangloa.domain.post.entity.Post;
import com.team.neorangloa.domain.post.entity.PostRaid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRaidRepository extends JpaRepository<PostRaid, Long> {
    @Query("SELECT pr FROM PostRaid pr WHERE pr.id = :postRaidId AND pr.removed = false ")
    Optional<PostRaid> findPostRaidById(Long postRaidId);

    @Query("SELECT pr FROM PostRaid pr WHERE pr.removed = false ORDER BY pr.id DESC")
    Page<PostRaid> findAllPostRaidByCreatedAtDesc(Pageable pageable);
}
