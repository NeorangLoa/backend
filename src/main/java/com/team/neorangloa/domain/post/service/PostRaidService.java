package com.team.neorangloa.domain.post.service;

import com.team.neorangloa.domain.post.dto.PostRaidListResponse;
import com.team.neorangloa.domain.post.dto.PostRaidRequest;
import com.team.neorangloa.domain.post.entity.PostRaid;
import com.team.neorangloa.domain.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PostRaidService {
    public void createNewPostRaid(PostRaidRequest postRaidRequest, User loginUser);

    public PostRaid findPostRaidById(Long postRaidId);

    public void updateViewCounts(Long postRaidId, HttpServletRequest request, HttpServletResponse response);

    public void increaseViewCounts(Long postRaidId);

    public List<PostRaidListResponse> getPosts(int page, int size);
    public List<PostRaidListResponse> getHotPosts();

    public void updatePost(User user, PostRaid postRaid, PostRaidRequest postRaidRequest);

    public void deletePost(User user, PostRaid postRaid);

    public void checkIsAuthor(User user, PostRaid postRaid);

    public int updateRaidPostRecommendation(User user, PostRaid postRaid);
}
