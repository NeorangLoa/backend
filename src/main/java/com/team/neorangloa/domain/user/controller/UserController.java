package com.team.neorangloa.domain.user.controller;


import com.team.neorangloa.domain.user.dto.ProfileResponse;
import com.team.neorangloa.domain.user.dto.SignupRequest;
import com.team.neorangloa.domain.user.dto.UpdateNicknameRequest;
import com.team.neorangloa.domain.user.dto.UpdatePasswordRequest;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.domain.user.exception.InvalidPasswordException;
import com.team.neorangloa.domain.user.mapper.UserMapper;
import com.team.neorangloa.domain.user.repository.UserRepository;
import com.team.neorangloa.domain.user.service.UserService;
import com.team.neorangloa.global.annotation.LoginRequired;
import com.team.neorangloa.global.annotation.LoginUser;
import com.team.neorangloa.global.result.ResultCode;
import com.team.neorangloa.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final HttpSession httpSession;

    @PostMapping
    public ResponseEntity<ResultResponse> signup(
            @RequestBody SignupRequest signupRequest) {
        userService.signup(signupRequest);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.USER_SIGNUP_SUCCESS));
    }

    @GetMapping("/user-info")
    @LoginRequired
    public ResponseEntity<ResultResponse> getLoginUserInfo(@LoginUser User loginUser) {
        return ResponseEntity.ok(ResultResponse.of(ResultCode.GET_LOGIN_USER_SUCCESS,ProfileResponse.of(loginUser)));
    }

    @PutMapping("/nickname")
    @LoginRequired
    public ResponseEntity<ResultResponse> updateNickname(@LoginUser User loginUser,
                                                         @RequestBody UpdateNicknameRequest updateNicknameRequest) {
        Long logInUserId = loginUser.getId();
        userService.updateNickname(logInUserId, updateNicknameRequest);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.UPDATE_USER_NICKNAME_SUCCESS,ProfileResponse.of(loginUser)));
    }

    @PutMapping("/password")
    @LoginRequired
    public ResponseEntity<ResultResponse> updatePassword(@LoginUser User loginUser,
                                                         @RequestBody UpdatePasswordRequest updatePasswordRequest) {
        Long logInUserId = loginUser.getId();
        if (!userService.isValidUser(logInUserId,updatePasswordRequest)) {
            throw new InvalidPasswordException();
        }
        userService.updatePassword(logInUserId, updatePasswordRequest);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.UPDATE_USER_PASSWORD_SUCCESS));
    }
}