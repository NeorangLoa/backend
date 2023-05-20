package com.team.neorangloa.domain.post.service;

import com.team.neorangloa.domain.post.dto.PostListResponse;
import com.team.neorangloa.domain.post.dto.PostRaidListResponse;
import com.team.neorangloa.domain.post.dto.PostRaidRequest;
import com.team.neorangloa.domain.post.dto.PostRequest;
import com.team.neorangloa.domain.post.entity.PostRaid;
import com.team.neorangloa.domain.raid.entity.Raid;

import java.util.List;

public interface PostRaidService {
    public void createNewPostRaid(PostRaidRequest postRaidRequest);

    public PostRaid findPostRaidById(Long postRaidId);

    public List<PostRaidListResponse> getPosts(int page, int size);

    public void updatePost(PostRaid postRaid, Raid raid, PostRaidRequest postRaidRequest);

    public void deletePost(PostRaid postRaid);
}
