package com.team.neorangloa.domain.post.service;

import com.team.neorangloa.domain.post.dto.PostListResponse;
import com.team.neorangloa.domain.post.dto.PostRequest;
import com.team.neorangloa.domain.post.entity.Post;
import com.team.neorangloa.domain.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PostService {
    public void createNewPost(PostRequest postRequest, User loginUser);

    public Post findPostById(Long postId);

//    public PostRecommendation findByClientAndPost(User user, Post post);

    public void updateViewCounts(Long postId, HttpServletRequest request, HttpServletResponse response);

    public void increaseViewCounts(Long postId);

    public List<PostListResponse> getPosts(int page, int size);

    public void updatePost(User user, Post post,PostRequest postRequest);

    public void deletePost(User user, Post post);

    public void checkIsAuthor(User user, Post post);

    public void updatePostRecommendation(User user, Post post);
}
