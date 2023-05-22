package com.team.neorangloa.domain.post.service;

import com.team.neorangloa.domain.post.PostRaidMapper;
import com.team.neorangloa.domain.post.dto.PostRaidListResponse;
import com.team.neorangloa.domain.post.dto.PostRaidRequest;
import com.team.neorangloa.domain.post.entity.PostRaid;
import com.team.neorangloa.domain.post.repository.PostRaidRepository;
import com.team.neorangloa.domain.raid.entity.Raid;
import com.team.neorangloa.domain.raid.repository.RaidRepository;
import com.team.neorangloa.global.error.ErrorCode;
import com.team.neorangloa.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostRaidServiceImpl implements PostRaidService{
    private final PostRaidRepository postRaidRepository;
    private final RaidRepository raidRepository;
    private final PostRaidMapper postRaidMapper;

    @Transactional
    @Override
    public void createNewPostRaid(PostRaidRequest postRaidRequest) {
        PostRaid postRaid = postRaidMapper.toEntity(postRaidRequest);
        Raid raid = raidRepository.findRaidByName(postRaidRequest.getRaidName()).orElseThrow(
                () -> new BusinessException(ErrorCode.RAID_NOT_FOUND_ERROR));
        postRaid.setRaid(raid);
        postRaidRepository.save(postRaid);
    }

    @Override
    public PostRaid findPostRaidById(Long postRaidId) {
        return postRaidRepository.findPostRaidById(postRaidId).orElseThrow(
                () -> new BusinessException(ErrorCode.POST_NOT_FOUND_ERROR));
    }

    @Transactional
    @Override
    public List<PostRaidListResponse> getPosts(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        List<PostRaid> postRaids = postRaidRepository.findAllPostRaidByCreatedAtDesc(pageRequest).getContent();
        return postRaidMapper.toDtoList(postRaids);
    }

    @Transactional
    @Override
    public void updatePost(PostRaid postRaid, PostRaidRequest postRaidRequest) {
        postRaid.updatePost(postRaidRequest);
        Raid raid = raidRepository.findRaidByName(postRaidRequest.getRaidName()).orElseThrow(
                () -> new BusinessException(ErrorCode.RAID_NOT_FOUND_ERROR));
        postRaid.setRaid(raid);
        postRaidRepository.save(postRaid);
    }

    @Transactional
    @Override
    public void deletePost(PostRaid postRaid) {
        postRaid.setRemoved(true);
        postRaidRepository.save(postRaid);
    }
}
