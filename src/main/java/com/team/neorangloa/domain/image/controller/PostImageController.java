package com.team.neorangloa.domain.image.controller;

import com.team.neorangloa.domain.image.service.ImageFileUploadService;
import com.team.neorangloa.global.result.ResultCode;
import com.team.neorangloa.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostImageController {
    private final ImageFileUploadService imageFileUploadService;

    @PostMapping("/{postId}/images")
    public ResponseEntity<ResultResponse> uploadImages(@PathVariable Long postId,
                                                   @RequestParam("files")List<MultipartFile> files) throws IOException {
        imageFileUploadService.upload(postId,files);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.IMAGE_UPLOAD_SUCCESS));
    }
}
