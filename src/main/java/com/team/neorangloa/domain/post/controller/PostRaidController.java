package com.team.neorangloa.domain.post.controller;

import com.team.neorangloa.domain.post.PostRaidMapper;
import com.team.neorangloa.domain.post.dto.PostListResponse;
import com.team.neorangloa.domain.post.dto.PostRaidListResponse;
import com.team.neorangloa.domain.post.dto.PostRaidRequest;
import com.team.neorangloa.domain.post.entity.Post;
import com.team.neorangloa.domain.post.entity.PostRaid;
import com.team.neorangloa.domain.post.service.PostRaidService;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.annotation.LoginRequired;
import com.team.neorangloa.global.annotation.LoginUser;
import com.team.neorangloa.global.result.ResultCode;
import com.team.neorangloa.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "raidPosts", description = "레이드 게시물 API")
@RequestMapping("/api/v1/postRaid")
@RequiredArgsConstructor
public class PostRaidController {
    private final PostRaidService postRaidService;

    private final PostRaidMapper postRaidMapper;

    @PostMapping
    @LoginRequired
    public ResponseEntity<ResultResponse> createNewPostRaid(@RequestBody @Valid PostRaidRequest postRaidRequest,
                                                            @LoginUser User loginUser) {
        postRaidService.createNewPostRaid(postRaidRequest, loginUser);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_CREATE_SUCCESS));
    }

    @GetMapping("/{postRaidId}")
    public ResponseEntity<ResultResponse> getPostRaid(@PathVariable Long postRaidId, HttpServletRequest request, HttpServletResponse response) {
        postRaidService.updateViewCounts(postRaidId, request, response);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_GET_SUCCESS,
                postRaidMapper.of(postRaidService.findPostRaidById(postRaidId))));
    }

    @GetMapping("/all/{page}")
    public ResponseEntity<ResultResponse> getPostRaids(@PathVariable Integer page
            ,@RequestParam(defaultValue = "20") Integer size ) {
        List<PostRaidListResponse> postRaids = postRaidService.getPosts(page,size);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_PAGING_GET_SUCCESS, postRaids));
    }

    @GetMapping("/hot")
    public ResponseEntity<ResultResponse> getHotPosts() {
        List<PostRaidListResponse> hotPostRaids = postRaidService.getHotPosts();
        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_HOT_GET_SUCCESS, hotPostRaids));
    }

    @PutMapping("/{postRaidId}")
    @LoginRequired
    public ResponseEntity<ResultResponse> updateRaidPost(@PathVariable Long postRaidId,
                                                         @RequestBody @Valid PostRaidRequest postRaidRequest,
                                                         @LoginUser User loginUser) {
        PostRaid postRaid = postRaidService.findPostRaidById(postRaidId);
        postRaidService.updatePost(loginUser, postRaid, postRaidRequest);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_UPDATE_SUCCESS));
    }

    @DeleteMapping("/{postRaidId}")
    @LoginRequired
    public ResponseEntity<ResultResponse> deleteRaidPost(@PathVariable Long postRaidId,
                                                         @LoginUser User loginUser) {
        PostRaid postRaid = postRaidService.findPostRaidById(postRaidId);
        postRaidService.deletePost(loginUser, postRaid);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_DELETE_SUCCESS));
    }

    @GetMapping("/recommend/{postRaidId}")
    @LoginRequired
    public ResponseEntity<ResultResponse> recommendRaidPost(@PathVariable Long postRaidId,
                                                        @LoginUser User loginUser){
        PostRaid postRaid = postRaidService.findPostRaidById(postRaidId);
        int postLike = postRaidService.updateRaidPostRecommendation(loginUser, postRaid);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_RECOMMENDATION_UPDATE_SUCCESS, postLike));
    }
}
