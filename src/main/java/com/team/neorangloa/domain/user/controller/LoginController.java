package com.team.neorangloa.domain.user.controller;

import com.team.neorangloa.domain.user.dto.LoginRequest;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.domain.user.exception.InvalidPasswordException;
import com.team.neorangloa.domain.user.service.LoginService;
import com.team.neorangloa.domain.user.service.UserService;
import com.team.neorangloa.global.annotation.LoginRequired;
import com.team.neorangloa.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.team.neorangloa.global.result.ResultCode.USER_LOGIN_SUCCESS;
import static com.team.neorangloa.global.result.ResultCode.USER_LOGOUT_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoginController {
    public static final String LOGIN_PATH = "/login";
    public static final String LOGOUT_PATH = "/logout";

    private final UserService userService;
    private final LoginService loginService;

    @PostMapping(LOGIN_PATH)
    public ResponseEntity<ResultResponse> login(HttpServletRequest request, @RequestBody @Valid LoginRequest loginRequest) {
        if (!loginService.isValidUser(loginRequest)) {
            throw new InvalidPasswordException();
        }
        User user = userService.findUserByEmail(loginRequest.getEmail());
        loginService.login(request,user.getId());
        return ResponseEntity.ok(ResultResponse.of(USER_LOGIN_SUCCESS));
    }

    @LoginRequired
    @GetMapping(LOGOUT_PATH)
    public ResponseEntity<ResultResponse> logout(HttpServletRequest request){
        loginService.logout(request);
        return ResponseEntity.ok(ResultResponse.of(USER_LOGOUT_SUCCESS));
    }
}
