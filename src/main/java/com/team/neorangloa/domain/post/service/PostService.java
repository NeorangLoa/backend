package com.team.neorangloa.domain.post.service;

import com.team.neorangloa.domain.post.dto.PostRequest;
import com.team.neorangloa.domain.post.entity.Post;

public interface PostService {
    public void createNewPost(PostRequest postRequest);

    public Post findPostById(Long postId);
}
