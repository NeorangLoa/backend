package com.team.neorangloa.domain.user.controller;

import com.team.neorangloa.domain.user.dto.LoginRequestDto;
import com.team.neorangloa.domain.user.dto.LoginUser;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.domain.user.exception.InvalidPasswordException;
import com.team.neorangloa.domain.user.service.LoginService;
import com.team.neorangloa.domain.user.service.UserService;
import com.team.neorangloa.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import static com.team.neorangloa.global.result.ResultCode.USER_LOGIN_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoginController {
    public static final String LOGIN_PATH = "/login";
    public static final String LOGOUT_PATH = "/logout";

    private final UserService userService;
    private final LoginService loginService;

    @PostMapping(LOGIN_PATH)
    public ResponseEntity<ResultResponse> login(@RequestBody LoginRequestDto loginRequestDto) {
        boolean isValidUser = loginService.isValidUser(loginRequestDto);

        if (!isValidUser) {
            throw new InvalidPasswordException();
        }
        User user = userService.findUserByEmail(loginRequestDto.getEmail());
        loginService.login(user.getId());
        return ResponseEntity.ok(ResultResponse.of(USER_LOGIN_SUCCESS));
    }

}
