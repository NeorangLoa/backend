package com.team.neorangloa.domain.comment.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "commentRaids", description = "레이드 게시물 댓글 API")
@RequestMapping("/api/v1/commentraid")
@RequiredArgsConstructor
public class CommentRaidController {
}
