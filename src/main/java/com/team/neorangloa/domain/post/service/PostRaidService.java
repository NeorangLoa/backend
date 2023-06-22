package com.team.neorangloa.domain.post.service;

import com.team.neorangloa.domain.post.dto.PostListResponse;
import com.team.neorangloa.domain.post.dto.PostRaidListResponse;
import com.team.neorangloa.domain.post.dto.PostRaidRequest;
import com.team.neorangloa.domain.post.dto.PostRequest;
import com.team.neorangloa.domain.post.entity.PostRaid;
import com.team.neorangloa.domain.raid.entity.Raid;
import com.team.neorangloa.domain.user.entity.User;

import java.util.List;

public interface PostRaidService {
    public void createNewPostRaid(PostRaidRequest postRaidRequest, User loginUser);

    public PostRaid findPostRaidById(Long postRaidId);

    public List<PostRaidListResponse> getPosts(int page, int size);

    public void updatePost(PostRaid postRaid, PostRaidRequest postRaidRequest);

    public void deletePost(PostRaid postRaid);
}
