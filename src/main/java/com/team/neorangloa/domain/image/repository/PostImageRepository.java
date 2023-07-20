package com.team.neorangloa.domain.image.repository;

import com.team.neorangloa.domain.image.entity.PostImage;
import com.team.neorangloa.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
    @Query("SELECT pi FROM PostImage pi WHERE pi.post = :post")
    List<PostImage> findAllByPost(Post post);
}
