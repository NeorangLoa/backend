package com.team.neorangloa.domain.post.controller;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "posts", description = "게시물 API")
@RequestMapping("/api/v1/post")
@RestController
public class PostController {
    @GetMapping()
    public String hello(){
        return "Hello World";
    }
}
