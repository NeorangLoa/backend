package com.team.neorangloa.domain.comment.controller;

import com.team.neorangloa.domain.comment.dto.CommentRaidRequest;
import com.team.neorangloa.domain.comment.dto.CommentRaidResponse;
import com.team.neorangloa.domain.comment.dto.CommentRaidUpdateRequest;
import com.team.neorangloa.domain.comment.entity.CommentRaid;
import com.team.neorangloa.domain.comment.service.CommentRaidService;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.annotation.LoginRequired;
import com.team.neorangloa.global.annotation.LoginUser;
import com.team.neorangloa.global.result.ResultCode;
import com.team.neorangloa.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "commentRaids", description = "레이드 게시물 댓글 API")
@RequestMapping("/api/v1/commentraid")
@RequiredArgsConstructor
public class CommentRaidController {
    private final CommentRaidService commentRaidService;

    @PostMapping
    @LoginRequired
    public ResponseEntity<ResultResponse> createCommentRaid(@RequestBody @Valid CommentRaidRequest request,
                                                            @LoginUser User loginUser) {
        commentRaidService.createCommentRaid(request, loginUser);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.REGISTER_COMMENT_SUCCESS));
    }

    @GetMapping("/{postRaidId}")
    public ResponseEntity<ResultResponse> getCommentRaids(@PathVariable Long postRaidId
            ,@RequestParam Integer page, @RequestParam(defaultValue = "20") Integer size) {
        List<CommentRaidResponse> commentRaids = commentRaidService.findCommentRaidByPostRaidId(page, size, postRaidId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.COMMENT_FIND_SUCCESS, commentRaids));
    }

    @PutMapping("/{commentRaidId}")
    @LoginRequired
    public ResponseEntity<ResultResponse> updateCommentRaid(@PathVariable Long commentRaidId,
                                                            @RequestBody @Valid CommentRaidUpdateRequest request,
                                                            @LoginUser User loginUser) {
        CommentRaid commentRaid = commentRaidService.findCommentRaidById(commentRaidId);
        commentRaidService.updateCommentRaid(loginUser, commentRaid, request);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.COMMENT_UPDATE_SUCCESS));
    }

    @DeleteMapping("/{commentRaidId}")
    @LoginRequired
    public ResponseEntity<ResultResponse> deleteCommentRaid(@PathVariable Long commentRaidId,
                                                            @LoginUser User loginUser) {
        CommentRaid commentRaid = commentRaidService.findCommentRaidById(commentRaidId);
        commentRaidService.deleteCommentRaid(loginUser, commentRaid);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.COMMENT_DELETE_SUCCESS));
    }
}
