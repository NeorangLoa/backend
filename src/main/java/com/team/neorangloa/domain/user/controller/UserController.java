package com.team.neorangloa.domain.user.controller;


import com.team.neorangloa.domain.user.dto.SignupRequestDto;
import com.team.neorangloa.domain.user.service.UserService;
import com.team.neorangloa.global.result.ResultCode;
import com.team.neorangloa.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ap1/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResultResponse> signup(
            @RequestBody SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.USER_SIGNUP_SUCCESS));
    }

    @GetMapping
    public ResponseEntity<ResultResponse> getUserProfile

}