package com.team.neorangloa.domain.post.service;

import com.team.neorangloa.domain.post.dto.PostListResponse;
import com.team.neorangloa.domain.post.dto.PostRequest;
import com.team.neorangloa.domain.post.entity.Post;

import java.util.List;

public interface PostService {
    public void createNewPost(PostRequest postRequest);

    public Post findPostById(Long postId);

    public List<PostListResponse> getPosts(int page, int size);

    public void updatePost(Post post,PostRequest postRequest);

    public void deletePost(Post post);
}
