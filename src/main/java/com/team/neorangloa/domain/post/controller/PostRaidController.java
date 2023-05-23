package com.team.neorangloa.domain.post.controller;

import com.team.neorangloa.domain.post.PostRaidMapper;
import com.team.neorangloa.domain.post.dto.PostRaidListResponse;
import com.team.neorangloa.domain.post.dto.PostRaidRequest;
import com.team.neorangloa.domain.post.entity.PostRaid;
import com.team.neorangloa.domain.post.service.PostRaidService;
import com.team.neorangloa.global.result.ResultCode;
import com.team.neorangloa.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResultResponse> createNewPostRaid(@RequestBody @Valid PostRaidRequest postRaidRequest) {
        postRaidService.createNewPostRaid(postRaidRequest);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_CREATE_SUCCESS));
    }

    @GetMapping("/{postRaidId}")
    public ResponseEntity<ResultResponse> getPostRaid(@PathVariable Long postRaidId) {
        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_GET_SUCCESS,
                postRaidMapper.of(postRaidService.findPostRaidById(postRaidId))));
    }

    @GetMapping("/all/{page}")
    public ResponseEntity<ResultResponse> getPostRaids(@PathVariable Integer page
            ,@RequestParam(defaultValue = "10") Integer size ) {
        List<PostRaidListResponse> postRaids = postRaidService.getPosts(page,size);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_PAGING_GET_SUCCESS, postRaids));
    }

    @PutMapping("/{postRaidId}")
    public ResponseEntity<ResultResponse> updateRaidPost(@PathVariable Long postRaidId,
                                                         @RequestBody @Valid PostRaidRequest postRaidRequest) {
        PostRaid postRaid = postRaidService.findPostRaidById(postRaidId);
        postRaidService.updatePost(postRaid, postRaidRequest);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_UPDATE_SUCCESS));
    }

    @DeleteMapping("/{postRaidId}")
    public ResponseEntity<ResultResponse> deleteRaidPost(@PathVariable Long postRaidId) {
        PostRaid postRaid = postRaidService.findPostRaidById(postRaidId);
        postRaidService.deletePost(postRaid);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_DELETE_SUCCESS));
    }
}
