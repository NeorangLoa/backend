package com.team.neorangloa.domain.user.controller;


import com.team.neorangloa.domain.user.dto.LoginUser;
import com.team.neorangloa.domain.user.dto.ProfileResponse;
import com.team.neorangloa.domain.user.dto.SignupRequestDto;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.domain.user.exception.UserNotFoundException;
import com.team.neorangloa.domain.user.repository.UserRepository;
import com.team.neorangloa.domain.user.service.UserService;
import com.team.neorangloa.global.annotation.LoginRequired;
import com.team.neorangloa.global.result.ResultCode;
import com.team.neorangloa.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    private final UserRepository userRepository;

    private final HttpSession httpSession;

    @PostMapping
    public ResponseEntity<ResultResponse> signup(
            @RequestBody SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.USER_SIGNUP_SUCCESS));
    }

    @GetMapping("/user-info")
    @LoginRequired
    public ResponseEntity<ResultResponse> getLoginUserInfo(){
        Long loginUserId =  (Long) httpSession.getAttribute("USER_ID");

        User loginUser = userRepository.findById(loginUserId).orElseThrow(UserNotFoundException::new);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.GET_LOGIN_USER_SUCCESS,ProfileResponse.of(loginUser)));
    }
}