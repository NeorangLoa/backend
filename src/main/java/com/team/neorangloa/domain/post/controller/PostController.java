package com.team.neorangloa.domain.post.controller;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import com.team.neorangloa.domain.post.dto.PostRequest;
import com.team.neorangloa.domain.post.entity.Post;
import com.team.neorangloa.domain.post.service.PostService;
import com.team.neorangloa.global.result.ResultCode;
import com.team.neorangloa.global.result.ResultResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Tag(name = "posts", description = "게시물 API")
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<ResultResponse> createNewPost(@RequestBody @Valid PostRequest postRequest) {
        postService.createNewPost(postRequest);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_CREATE_SUCCESS));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ResultResponse> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_GET_SUCCESS, postService.findPostById(postId)));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<ResultResponse> updatePost(@PathVariable Long postId,
                                                     @RequestBody @Valid PostRequest postRequest) {
        Post post = postService.findPostById(postId);
        postService.updatePost(post, postRequest);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_UPDATE_SUCCESS));
    }
}
