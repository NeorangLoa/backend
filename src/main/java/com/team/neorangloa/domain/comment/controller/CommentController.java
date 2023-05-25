package com.team.neorangloa.domain.comment.controller;

import com.team.neorangloa.domain.comment.dto.CommentRequest;
import com.team.neorangloa.domain.comment.dto.CommentResponse;
import com.team.neorangloa.domain.comment.dto.CommentUpdateRequest;
import com.team.neorangloa.domain.comment.entity.Comment;
import com.team.neorangloa.domain.comment.service.CommentService;
import com.team.neorangloa.global.result.ResultCode;
import com.team.neorangloa.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "comments", description = "자유게시글 댓글 API")
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<ResultResponse> createComment(@RequestBody @Valid CommentRequest commentRequest) {
        commentService.createComment(commentRequest);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.REGISTER_COMMENT_SUCCESS));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ResultResponse> getComments(@PathVariable Long postId
            ,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size ) {
        List<CommentResponse> comments = commentService.findCommentByPostId(page, size, postId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.COMMENT_FIND_SUCCESS,comments));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<ResultResponse> updateComment(@PathVariable Long commentId,
                                                        @RequestBody @Valid CommentUpdateRequest request) {
        Comment comment = commentService.findCommentById(commentId);
        commentService.updateComment(comment, request);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.COMMENT_UPDATE_SUCCESS));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResultResponse> deleteComment(@PathVariable Long commentId) {
        Comment comment = commentService.findCommentById(commentId);
        commentService.deleteComment(comment);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.COMMENT_DELETE_SUCCESS));
    }
}
