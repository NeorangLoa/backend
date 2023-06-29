package com.team.neorangloa.domain.post.controller;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import com.team.neorangloa.domain.post.PostMapper;
import com.team.neorangloa.domain.post.dto.PostListResponse;
import com.team.neorangloa.domain.post.dto.PostRequest;
import com.team.neorangloa.domain.post.entity.Post;
import com.team.neorangloa.domain.post.service.PostService;
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
@Tag(name = "posts", description = "게시물 API")
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    private final PostMapper postMapper;

    @PostMapping
    @LoginRequired
    public ResponseEntity<ResultResponse> createNewPost(@RequestBody @Valid PostRequest postRequest,
                                                        @LoginUser User loginUser) {
        postService.createNewPost(postRequest, loginUser);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_CREATE_SUCCESS));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ResultResponse> getPost(@PathVariable Long postId, HttpServletRequest request, HttpServletResponse response) {
        postService.updateViewCounts(postId, request, response);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_GET_SUCCESS
                ,postMapper.of(postService.findPostById(postId))));
    }

    @GetMapping("/all/{page}")
    public ResponseEntity<ResultResponse> getPosts(@PathVariable Integer page
            ,@RequestParam(defaultValue = "20") Integer size) {
        List<PostListResponse> posts = postService.getPosts(page,size);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_PAGING_GET_SUCCESS, posts));
    }

    @PutMapping("/{postId}")
    @LoginRequired
    public ResponseEntity<ResultResponse> updatePost(@PathVariable Long postId,
                                                     @RequestBody @Valid PostRequest postRequest,
                                                     @LoginUser User loginUser) {
        Post post = postService.findPostById(postId);
        postService.updatePost(loginUser,post, postRequest);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_UPDATE_SUCCESS));
    }

    @DeleteMapping("/{postId}")
    @LoginRequired
    public ResponseEntity<ResultResponse> deletePost(@PathVariable Long postId,
                                                     @LoginUser User loginUser) {
        Post post = postService.findPostById(postId);
        postService.deletePost(loginUser, post);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_DELETE_SUCCESS));
    }
    @GetMapping("/recommend/{postId}")
    @LoginRequired
    public ResponseEntity<ResultResponse> recommendPost(@PathVariable Long postId,
                                                        @LoginUser User loginUser){
        Post post = postService.findPostById(postId);
        postService.updatePostRecommendation(loginUser, post);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.POST_RECOMMENDATION_UPDATE_SUCCESS));
    }
}
