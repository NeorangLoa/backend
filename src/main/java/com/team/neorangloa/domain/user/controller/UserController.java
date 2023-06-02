package com.team.neorangloa.domain.user.controller;


import com.team.neorangloa.domain.user.dto.UserInfoResponse;
import com.team.neorangloa.domain.user.dto.SignupRequest;
import com.team.neorangloa.domain.user.dto.UpdateNicknameRequest;
import com.team.neorangloa.domain.user.dto.UpdatePasswordRequest;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.domain.user.exception.InvalidPasswordException;
import com.team.neorangloa.domain.user.service.UserService;
import com.team.neorangloa.global.annotation.LoginRequired;
import com.team.neorangloa.global.annotation.LoginUser;
import com.team.neorangloa.global.result.ResultCode;
import com.team.neorangloa.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResultResponse> signup(
            @RequestBody @Valid SignupRequest signupRequest) {
        userService.signup(signupRequest);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.USER_SIGNUP_SUCCESS));
    }

    @GetMapping("/user-info/{userId}")
    public ResponseEntity<ResultResponse> getLoginUserInfo(@PathVariable Long userId) {
        UserInfoResponse userInfoResponse = userService.findUserDtoById(userId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.GET_USER_SUCCESS, userInfoResponse));
    }

    @PutMapping("/nickname")
    @LoginRequired
    public ResponseEntity<ResultResponse> updateNickname(@LoginUser User loginUser,
                                                         @RequestBody @Valid UpdateNicknameRequest updateNicknameRequest) {
        Long logInUserId = loginUser.getId();
        userService.updateNickname(logInUserId, updateNicknameRequest);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.UPDATE_USER_NICKNAME_SUCCESS, UserInfoResponse.of(loginUser)));
    }

    @PutMapping("/password")
    @LoginRequired
    public ResponseEntity<ResultResponse> updatePassword(@LoginUser User loginUser,
                                                         @RequestBody @Valid UpdatePasswordRequest updatePasswordRequest) {
        Long logInUserId = loginUser.getId();
        if (!userService.isValidPassword(logInUserId,updatePasswordRequest)) {
            throw new InvalidPasswordException();
        }
        userService.updatePassword(logInUserId, updatePasswordRequest);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.UPDATE_USER_PASSWORD_SUCCESS));
    }
}