package com.team.neorangloa.domain.user.controller;


import com.team.neorangloa.domain.user.dto.LoginRequest;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.domain.user.exception.InvalidPasswordException;
import com.team.neorangloa.domain.user.service.LoginService;
import com.team.neorangloa.domain.user.service.UserService;
import com.team.neorangloa.global.annotation.LoginRequired;
import com.team.neorangloa.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @PostMapping(LOGIN_PATH)
    public ResponseEntity<ResultResponse> login(HttpServletRequest request,
                                                @RequestBody @Valid LoginRequest loginRequest,
                                                HttpServletResponse response) {
        if (!loginService.isValidUser(loginRequest)) {
            throw new InvalidPasswordException();
        }
        User user = userService.findUserByEmail(loginRequest.getEmail());
        String sessionId = loginService.login(request,user.getId());
        // 쿠키 생성
        Cookie sessionCookie = new Cookie("sessionId", sessionId);
        sessionCookie.setMaxAge(3600); // 쿠키 유효 시간 설정
        sessionCookie.setPath("/"); // 쿠키의 유효 경로 설정
        response.addCookie(sessionCookie); // Response Header에 쿠키 추가

        return ResponseEntity.ok().header("Set-Cookie", sessionCookie.toString()).body(ResultResponse.of(USER_LOGIN_SUCCESS));

    }

    @LoginRequired
    @GetMapping(LOGOUT_PATH)
    public ResponseEntity<ResultResponse> logout(HttpServletRequest request){
        loginService.logout(request);
        return ResponseEntity.ok(ResultResponse.of(USER_LOGOUT_SUCCESS));
    }
}
