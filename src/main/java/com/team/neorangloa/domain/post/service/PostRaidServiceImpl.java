package com.team.neorangloa.domain.post.service;

import com.team.neorangloa.domain.post.PostRaidMapper;
import com.team.neorangloa.domain.post.dto.PostRaidListResponse;
import com.team.neorangloa.domain.post.dto.PostRaidRequest;
import com.team.neorangloa.domain.post.entity.PostRaid;
import com.team.neorangloa.domain.post.repository.PostRaidRepository;
import com.team.neorangloa.domain.raid.entity.Raid;
import com.team.neorangloa.domain.raid.repository.RaidRepository;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.error.ErrorCode;
import com.team.neorangloa.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostRaidServiceImpl implements PostRaidService{
    private final PostRaidRepository postRaidRepository;
    private final RaidRepository raidRepository;
    private final PostRaidMapper postRaidMapper;

    @Transactional
    @Override
    public void createNewPostRaid(PostRaidRequest postRaidRequest, User loginUser) {
        PostRaid postRaid = postRaidMapper.toEntity(postRaidRequest, loginUser);
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

    // 조회수 중복 방지 함수
    @Override
    public void updateViewCounts(Long postRaidId, HttpServletRequest request, HttpServletResponse response) {
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("postRaidView")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if(!oldCookie.getValue().contains("[" + postRaidId.toString() + "]")){
                increaseViewCounts(postRaidId);
                oldCookie.setValue(oldCookie.getValue() + "_[" + postRaidId + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
            }
        } else {
            increaseViewCounts(postRaidId);
            Cookie newCookie = new Cookie("postRaidView", "[" + postRaidId + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            //newCookie.setDomain(".우리가 사용하는 도메인 주소"); // 우리가 사용하는 도메인 주소 예시: ".tistory.com"
            response.addCookie(newCookie);
        }
    }

    // 조회수 증가 함수
    @Override
    @Transactional
    public void increaseViewCounts(Long postRaidId) {
        postRaidRepository.updateViewCounts(postRaidId);
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
